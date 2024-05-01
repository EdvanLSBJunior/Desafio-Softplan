import { useState } from "react";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import PropTypes from 'prop-types';

const FormularioTransacao = ({ onSubmit }) => {
  const [idCliente, setIdCliente] = useState("");
  const [valor, setValor] = useState(0);
  const [tipo, setTipo] = useState("");
  const [descricao, setDescricao] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();
    onSubmit({ idCliente, valor, tipo, descricao });
  };

  return (
    <Box
      component="form"
      sx={{
        display: "flex",
        flexDirection: "column",
        gap: 1,
        alignItems: "center",
        justifyContent: "center",
        "& .MuiTextField-root": {
          width: "25ch",
        },
        "& .MuiButton-root": {
          width: "25ch",
        },
      }}
      autoComplete="off"
      onSubmit={handleSubmit}
    >
      <div>
        <TextField
          required
          id="idCliente"
          label="Id do Cliente"
          variant="outlined"
          value={idCliente}
          onChange={(event) => setIdCliente(event.target.value)}
        />
      </div>
      <div>
        <TextField
          required
          id="valor"
          label="Valor"
          variant="outlined"
          type="number"
          value={valor}
          onChange={(event) => setValor(event.target.value)}
        />
      </div>
      <div>
        <TextField
          required
          id="tipo"
          label="Tipo"
          variant="outlined"
          value={tipo}
          onChange={(event) => setTipo(event.target.value)}
        />
      </div>
      <div>
        <TextField
          required
          id="descricao"
          label="Descrição"
          variant="outlined"
          multiline
          rows={2}
          value={descricao}
          onChange={(event) => setDescricao(event.target.value)}
        />
      </div>
      <div>
        <Button type="submit" variant="contained" color="primary">
          Realizar Transação
        </Button>
      </div>
    </Box>
  );
};

FormularioTransacao.propTypes = {
    onSubmit: PropTypes.func.isRequired,
  };

export default FormularioTransacao;
