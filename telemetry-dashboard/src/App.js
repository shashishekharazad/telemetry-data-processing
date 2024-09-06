import React from 'react';
import './App.css';
import TelemetryChart from './components/TelemetryChart';

function App() {
    return (
        <div className="App">
            <div className="App-header">
                <h1>Open Telemetry Dashboard</h1>
                <h3>iDRAC Simulation</h3>
            </div>
            <div className="App-heading">
                <h2>Telemetry Data</h2>
            </div>

            <TelemetryChart/>
        </div>
    );
}

export default App;
