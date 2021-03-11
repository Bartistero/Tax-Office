package com.sterniczuk.data;

import com.sterniczuk.Receipt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
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

        public void addNewReceipt(Receipt receipt){

                String invoiceNumber = (String)jdbc.queryForObject("SELECT MAX(invoiceNumber) from receipt where InvoiceType=?", String.class,receipt.getType());

                boolean isNull = false;
                try {
                        System.out.println(invoiceNumber);
                    invoiceNumber.isEmpty();
                } catch (NullPointerException npe) {
                    isNull = true;
                }

                if(isNull){
                        receipt.setInvoiceNumber("1");
                }else{
                       int number =  Integer.parseInt(invoiceNumber);
                       number += 1;
                        receipt.setInvoiceNumber(Integer.toString(number));
                }


                final String sql = "Insert into Receipt(INVOICENUMBER,IDUSERS,CUSTOMERNAME,ADDRESS,NETTOPRICE,VAT,INVOICETYPE, DATE,CUSTOMERNIP) VALUES(?,?,?,?,?,?,?,?,?)";
                jdbc.update(sql,
                        receipt.getInvoiceNumber(),
                        receipt.getIdUser(),
                        receipt.getCustomerName(),
                        receipt.getAddress(),
                        receipt.getNettoPrice(),
                        receipt.getVAT(),
                        receipt.getType(),
                        receipt.getDate(),
                        receipt.getCustomerNIP());
        }

        public void editReceipt(Receipt receipt){
                final String sql = "update receipt set customerNip =?, customerName=?, address=?, nettoPrice=? where invoiceNumber=? and invoiceType=?";
               int rows =  jdbc.update(sql, receipt.getCustomerNIP(),receipt.getCustomerName(),receipt.getAddress(), receipt.getNettoPrice(),receipt.getInvoiceNumber(),receipt.getType());
                System.out.println(
                        "numer rachunku: "  + receipt.getInvoiceNumber() +
                                " typ rachunku:  " + receipt.getType() +
                                " NIP: " + receipt.getCustomerNIP() +
                                " Nazwa: " + receipt.getCustomerName() +
                                " address: " + receipt.getAddress() +
                                " cena: " + receipt.getNettoPrice() +
                                " zakutalizowano: " + rows);
        }

        private Receipt mapRowToReceipt(ResultSet rs, int rowNum) throws SQLException {
                Receipt receipt= new Receipt();

                receipt.setId(rs.getLong("idReceipt"));
                receipt.setInvoiceNumber(rs.getString("invoiceNumber"));
                receipt.setIdUser(rs.getString("idUsers"));
                receipt.setCustomerName(rs.getString("CustomerName"));
                receipt.setAddress(rs.getString("address"));
                receipt.setNettoPrice(Double.parseDouble(rs.getString("nettoPrice")));
                receipt.setVAT(Integer.parseInt(rs.getString("vat")));
                receipt.setType(rs.getString("Invoicetype"));
                receipt.setDate(rs.getDate("date"));
                receipt.setCustomerNIP(rs.getString("customerNIP"));

                return receipt;
        }

}
