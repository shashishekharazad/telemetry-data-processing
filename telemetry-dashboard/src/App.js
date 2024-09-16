import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import TelemetryChart from './components/TelemetryChart';
import NodeDetails from './components/NodeDetails'; // Import the new component

function App() {
    return (
        <Router>
            <div className="App">
                <div className="App-header">
                    <h1>Open Telemetry Dashboard</h1>
                    <h3>iDRAC Simulation</h3>
                </div>
                <div className="App-heading">
                    <h2>Telemetry Data</h2>
                </div>
                <Routes>
                    <Route path="/" element={<TelemetryChart />} />
                    <Route path="/node/:node" element={<NodeDetails />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
