import { useEffect, useState } from "react";
import TableExtrato from "../components/TableExtrato.jsx";
import PropTypes from "prop-types";

const Extrato = () => {
    const [extrato, setExtrato] = useState({});
    const [idCliente, setIdCliente] = useState(null);

          useEffect(() => {
        const updateIdClienteFromLocalStorage = () => {
            const storedIdCliente = window.localStorage.getItem("idCliente");
            setIdCliente(storedIdCliente);
        };

        updateIdClienteFromLocalStorage();

        const handleLocalStorageChange = () => {
            updateIdClienteFromLocalStorage();
        };

        window.addEventListener("storage", handleLocalStorageChange);

        return () => {
            window.removeEventListener("storage", handleLocalStorageChange);
        };
    }, []);

    useEffect(() => {
        if (idCliente !== "null") {
          fetch(`http://localhost:9999/clientes/${idCliente}/extrato`)
            .then((response) => response.json())
            .then((data) => {
              setExtrato(data);
            })
            .catch((error) => {
              console.error("Erro ao obter o extrato:", error);
            });
        }
      }, [idCliente]);

  return (
    <div>
      <h1 style={{ textAlign: "center" }}>Extrato</h1>
      <TableExtrato saldo={extrato.saldo} ultimasTransacoes={extrato.ultimas_transacoes} />
    </div>
  );
};

Extrato.propTypes = {
  idCliente: PropTypes.number,
};

export default Extrato;
