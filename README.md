import React, { useEffect, useState, useRef } from 'react';
import { BrowserMultiFormatReader } from '@zxing/library';

const BarcodeScanner = () => {
  const [barcode, setBarcode] = useState(null);
  const [error, setError] = useState(null);
  const videoRef = useRef(null);  // Reference to the video element
  const [scanning, setScanning] = useState(false);  // Flag for scanning state

  const startScanner = () => {
    const codeReader = new BrowserMultiFormatReader();
    codeReader
      .listVideoInputDevices()  // Get all available video devices
      .then((videoInputDevices) => {
        if (videoInputDevices.length > 0) {
          const firstDeviceId = videoInputDevices[0].deviceId;
          // Start scanning from the first available camera
          codeReader.decodeFromVideoDevice(firstDeviceId, videoRef.current, (result, err) => {
            if (result) {
              setBarcode(result.getText());  // Set the scanned barcode value
              stopScanner(codeReader);  // Stop scanning after the first successful scan
            }
            if (err && !(err instanceof NotFoundException)) {
              setError(err);  // Handle other errors
            }
          });
          setScanning(true);
        } else {
          setError('No video devices found.');
        }
      })
      .catch((err) => {
        setError('Error initializing scanner: ' + err.message);
      });
  };

  const stopScanner = (codeReader) => {
    codeReader.reset();  // Reset the scanner when done
    setScanning(false);
  };

  const handleStart = () => {
    setBarcode(null);  // Clear previous barcode
    setError(null);  // Clear previous error
    startScanner();
  };

  const handleStop = () => {
    if (videoRef.current) {
      videoRef.current.srcObject.getTracks().forEach((track) => track.stop());  // Stop the video feed
    }
    setScanning(false);
  };

  useEffect(() => {
    // Cleanup the camera feed when component is unmounted
    return () => {
      if (videoRef.current) {
        videoRef.current.srcObject?.getTracks().forEach((track) => track.stop());
      }
    };
  }, []);

  return (
    <div>
      <h2>React Barcode Scanner</h2>
      <div>
        <button onClick={handleStart} disabled={scanning}>Start Scanning</button>
        <button onClick={handleStop} disabled={!scanning}>Stop Scanning</button>
      </div>
      <video ref={videoRef} width="100%" height="auto" />
      <div>
        {barcode ? (
          <p>Scanned Barcode: {barcode}</p>
        ) : (
          <p>{error ? `Error: ${error.message}` : 'Scanning... Please hold the barcode in front of the camera.'}</p>
        )}
      </div>
    </div>
  );
};

export default BarcodeScanner;
