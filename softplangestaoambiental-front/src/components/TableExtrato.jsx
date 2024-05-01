import {
  Typography,
  Paper,
  Table,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
} from "@mui/material";
import PropTypes from "prop-types";
import { format } from "date-fns";

const TableExtrato = ({ saldo, ultimasTransacoes }) => {
  const saldoTotal = saldo ? saldo.total.toFixed(2) : "";
  const saldoLimite = saldo ? saldo.limite.toFixed(2) : "";
  const dataExtrato = saldo ? format(new Date(saldo.data_extrato), "dd/MM/yyyy HH:mm:ss") : "";

  return (
    <div>
      <Paper sx={{ padding: 20 }}>
        <Typography variant="h6" align="center" marginBottom="2%">
          Extrato Bancário
        </Typography>
        <Typography variant="h9" align="left" marginBottom="2%">
          Data do Extrato: { dataExtrato }
        </Typography>

        <Table>
          <TableHead>
            <TableRow>
              <TableCell align="left">Saldo Total</TableCell>
              <TableCell align="right">Valor</TableCell>
            </TableRow>
          </TableHead>

          <TableBody>
            <TableRow>
              <TableCell align="left">Disponível</TableCell>
              <TableCell align="right">{saldoTotal}</TableCell>
            </TableRow>
            <TableRow>
              <TableCell align="left">Limite</TableCell>
              <TableCell align="right">{saldoLimite}</TableCell>
            </TableRow>
          </TableBody>
        </Table>

        <Typography variant="h6" align="center" margin="2%">
          Últimas Transações
        </Typography>

        <Table>
          <TableHead>
            <TableRow>
              <TableCell align="left">Data</TableCell>
              <TableCell align="left">Tipo</TableCell>
              <TableCell align="left">Descrição</TableCell>
              <TableCell align="right">Valor</TableCell>
            </TableRow>
          </TableHead>

          <TableBody>
            {ultimasTransacoes ? (
              ultimasTransacoes.map((transacao) => (
                <TableRow key={transacao.realizadaEm}>
                  <TableCell align="left">
                    {new Date(transacao.realizadaEm).toLocaleDateString()}
                  </TableCell>
                  <TableCell align="left">
                    {transacao.tipo === "r" ? "Recebimento" : "Débito"}
                  </TableCell>
                  <TableCell align="left">{transacao.descricao}</TableCell>
                  <TableCell align="right">
                    {transacao.valor?.toFixed(2)}
                  </TableCell>
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell colSpan={4} align="center">
                  Nenhuma transação disponível
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </Paper>
    </div>
  );
};

TableExtrato.propTypes = {
  saldo: PropTypes.object,
  ultimasTransacoes: PropTypes.array,
};

export default TableExtrato;
