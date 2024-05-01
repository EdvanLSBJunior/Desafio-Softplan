import FormularioTransacao from "../components/FormTransacao.jsx";
import PropTypes from 'prop-types';


const Transacoes = () => {

  const handleSubmit = (dadosTransacao) => {
    window.localStorage.setItem("idCliente", null);
    const { idCliente, valor, tipo, descricao } = dadosTransacao;
    fetch(`http://localhost:9999/clientes/${idCliente}/transacoes`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ valor, tipo, descricao }),
    })
      .then((response) => {
      response.json();
      window.localStorage.setItem("idCliente", idCliente);
      })
      .catch((error) => {
        console.error("Erro ao realizar a transação:", error);
      });
  };

  return (
    <div>
      <h1 style={{ textAlign: "center" }}>Transações</h1>
      <FormularioTransacao onSubmit={handleSubmit} />
    </div>
  );
};

Transacoes.propTypes = {
  atualizarExtrato: PropTypes.func,
};

export default Transacoes;

