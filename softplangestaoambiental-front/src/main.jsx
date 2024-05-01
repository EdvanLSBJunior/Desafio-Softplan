import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Transacoes from './pages/Transacoes.jsx';
import Extrato from './pages/Extrato.jsx';
import './index.css';
import Home  from './pages/Home.jsx';

const router = createBrowserRouter([
  {
    element: <App />,
    children: [
      {
        path: '/',
        element: <Home />
      },
      {
        path: '/transacoes',
        element: <Transacoes />
      },
      {
        path: '/extrato',
        element: <Extrato />
      }
    ]
  }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
