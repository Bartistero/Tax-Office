package com.sterniczuk.data;

import com.sterniczuk.Receipt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcReceiptRepository{


        private JdbcTemplate jdbc;

        public JdbcReceiptRepository(JdbcTemplate jdbc){
                this.jdbc = jdbc;
        }

        public List<Receipt> getALLRecepit(String idUser, String Month) throws SQLException{
                List<Receipt> receipts=  jdbc.query("Select * from Receipt where idUsers=? and MONTH(DATE)=?", this::mapRowToReceipt,idUser, Month);
                return receipts;
                //SELECT * FROM RECEIPT  where IdUsers = '1' and MONTH(DATE)='01'
        }

        public Receipt mapRowToReceipt(ResultSet rs, int rowNum) throws SQLException {
                Receipt receipt= new Receipt();

                receipt.setId(rs.getLong("idReceipt"));
                receipt.setInvoiceNumber(rs.getString("invoiceNumber"));
                receipt.setIdUser(rs.getString("idUsers"));
                receipt.setCustomerName(rs.getString("CustomerName"));
                receipt.setAddress(rs.getString("address"));
                receipt.setNettoPrice(Float.parseFloat(rs.getString("nettoPrice")));
                receipt.setVAT(Integer.parseInt(rs.getString("vat")));
                receipt.setType(rs.getString("Invoicetype"));
                receipt.setDate(rs.getDate("date"));

                return receipt;
        }
}
