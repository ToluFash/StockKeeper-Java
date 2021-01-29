package com.stockkeeper.Controller;

import com.stockkeeper.Controller.helpers.Encrypt;
import com.stockkeeper.Models.account.ExpenseAccount;
import com.stockkeeper.Models.customexceptions.NoUserFoundException;
import com.stockkeeper.Models.damages.Damages;
import com.stockkeeper.Models.debtors.Creditors;
import com.stockkeeper.Models.debtors.Debtors;
import com.stockkeeper.Controller.helpers.json.simple.JSONObject;
import com.stockkeeper.Controller.helpers.json.simple.parser.JSONParser;
import com.stockkeeper.Models.product.Product;
import com.stockkeeper.Models.purchases.Purchases;
import com.stockkeeper.Models.returnsinward.ReturnsInward;
import com.stockkeeper.Models.returnsoutward.ReturnsOutward;
import com.stockkeeper.Models.sales.Sales;
import com.stockkeeper.Models.stock.Stock;
import com.stockkeeper.Models.user.Entity;
import com.stockkeeper.Models.user.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class API {

    private final static String endpoint = "http://127.0.0.1:8000/api/stockkeeper/";
    public final static String key = "hallo";

    public static String getLongDateString(GregorianCalendar gregorianCalendar, Entity entity){
        return gregorianCalendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, entity.getOrganizationSettings().getLocaleSettings().getLocale())+ ", " +
                gregorianCalendar.getDisplayName(Calendar.MONTH,Calendar.LONG, entity.getOrganizationSettings().getLocaleSettings().getLocale()) + " " +
                gregorianCalendar.get(Calendar.DAY_OF_MONTH) + ", " +
                gregorianCalendar.get(Calendar.YEAR);
    }

    public static String getShortDateString(GregorianCalendar gregorianCalendar, Entity entity){
        return gregorianCalendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, entity.getOrganizationSettings().getLocaleSettings().getLocale())+ ", " +
                gregorianCalendar.getDisplayName(Calendar.MONTH,Calendar.LONG, entity.getOrganizationSettings().getLocaleSettings().getLocale()) + " " +
                gregorianCalendar.get(Calendar.DAY_OF_MONTH) + ", " +
                gregorianCalendar.get(Calendar.YEAR);
    }
    public static GregorianCalendar getTodayDate(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{

        try{
            String username= user.getName();
            String password = user.getPassword();
            Date date = new Date();
            String dataSend = "{"+
                    "\"entity\":\""+entity.getName()+
                    "\", \"username\":\"" + username +
                    "\", \"password\":\"" + password +
                    "\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getDate/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(data.toString());

            return new GregorianCalendar(Integer.parseInt(obj.get("year").toString()),Integer.parseInt(obj.get("month").toString())-1,Integer.parseInt(obj.get("day").toString()));

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }
    }

    public synchronized static User getUser(String entity,String username, String password) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{
            String dataSend = "{"+"\"entity\":\""+entity+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getUser/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new User().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }
    }

    public synchronized static Entity getEntity(String entity, String username, String password) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{
            String dataSend = "{"+"\"entity\":\""+entity+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getEntity/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new Entity().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }
    }

    public synchronized static ArrayList<Product> getProducts(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{
            String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+"\"entity\":\""+entity.getName()+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            System.out.println(dataSend);
            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getProducts/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return Product.constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }
    }

    public synchronized static Stock getStock(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{
            String username= user.getName();
            String password = user.getPassword();
            Date date = new Date();
            String dataSend = "{"+
                    "\"entity\":\""+entity.getName()+
                    "\", \"username\":\"" + username +
                    "\", \"password\":\"" + password +
                    "\", \"year\":\"" + date.getYear() +
                    "\", \"month\":\"" + date.getMonth() +
                    "\", \"day\":\"" + date.getDate() +
                    "\", \"hours\":\"" + date.getHours() +
                    "\", \"minutes\":\"" + date.getMinutes() +
                    "\", \"seconds\":\"" + date.getSeconds() +
                    "\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getStock/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new Stock().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            ioe.printStackTrace();
            throw new Exception(ioe.getMessage());
        }
    }




    public synchronized static Purchases getPurchases(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{
            String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+"\"entity\":\""+entity.getName()+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getPurchases/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new Purchases().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            ioe.printStackTrace();
            throw new Exception(ioe.getMessage());
        }
    }


    public synchronized static Sales getSales(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{
            String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+"\"entity\":\""+entity.getName()+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getSales/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new Sales().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            ioe.printStackTrace();
            throw new Exception(ioe.getMessage());
        }
    }


    public synchronized static Sales getSales(Entity entity, User user, GregorianCalendar gotoDate) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{
            String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+
                    "\"entity\":\""+entity.getName()+
                    "\", \"username\":\"" + username +
                    "\", \"password\":\"" + password +
                    "\", \"year\":\"" + gotoDate.get(Calendar.YEAR) +
                    "\", \"month\":\"" + (gotoDate.get(Calendar.MONTH)+1) +
                    "\", \"day\":\"" + gotoDate.get(Calendar.DATE) +
                    "\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getSalesAtDate/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new Sales().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }
    }



    public static ReturnsInward getReturnsInward(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+"\"entity\":\""+entity.getName()+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getReturnsInward/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new ReturnsInward().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }
    }



    public static ReturnsOutward getReturnsOutward(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+"\"entity\":\""+entity.getName()+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getReturnsOutward/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new ReturnsOutward().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }
    }


    public static Damages getDamages(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+"\"entity\":\""+entity.getName()+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getDamages/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new Damages().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }
    }

    public static Debtors getDebtors(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+"\"entity\":\""+entity.getName()+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getDebtors/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new Debtors().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }
    }

    public static Creditors getCreditors(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+"\"entity\":\""+entity.getName()+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getCreditors/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new Creditors().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }
    }
    public static ExpenseAccount getExpenses(Entity entity, User user) throws NoUserFoundException, FileNotFoundException, Exception, IOException{
        try{String username= user.getName();
            String password = user.getPassword();
            String dataSend = "{"+"\"entity\":\""+entity.getName()+"\", \"username\":\"" + username +"\", \"password\":\"" + password +"\"}";
            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getExpenses/").openConnection();
            connection.setRequestMethod("POST");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
            return new ExpenseAccount().constructFromJson(data.toString());

        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }
    }


    public static void putSale(Entity entity, User user, String ticket) throws Exception{

        String username= user.getName();
        String password = user.getPassword();

        try{
            String dataSend = ticket;


            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getSales/").openConnection();
            connection.setRequestMethod("PUT");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
        }
        catch (MalformedURLException mue){
        throw new NoUserFoundException();
    }
        catch (FileNotFoundException ioe){
        throw new FileNotFoundException(ioe.getMessage());
    }

        catch (Exception ioe){
        throw new Exception(ioe.getMessage());
    }

    }
    public static void putPurchase(Entity entity, User user, String ticket) throws Exception{

        String username= user.getName();
        String password = user.getPassword();

        try{
            String dataSend = ticket;


            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getPurchases/").openConnection();
            connection.setRequestMethod("PUT");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }

    }
    public static void putReturnInward(Entity entity, User user, String ticket) throws Exception{

        String username= user.getName();
        String password = user.getPassword();

        try{
            String dataSend = ticket;


            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getReturnsInward/").openConnection();
            connection.setRequestMethod("PUT");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }

    }
    public static void putReturnOutward(Entity entity, User user, String ticket) throws Exception{

        String username= user.getName();
        String password = user.getPassword();

        try{
            String dataSend = ticket;


            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getReturnsOutward/").openConnection();
            connection.setRequestMethod("PUT");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }

    }

    public static void putDamages(Entity entity, User user, String ticket) throws Exception{

        String username= user.getName();
        String password = user.getPassword();

        try{
            String dataSend = ticket;


            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getDamages/").openConnection();
            connection.setRequestMethod("PUT");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }

    }

    public static void putDebtors(Entity entity, User user, String ticket) throws Exception{

        String username= user.getName();
        String password = user.getPassword();

        try{
            String dataSend = ticket;


            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getDebtors/").openConnection();
            connection.setRequestMethod("PUT");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }

    }

    public static void putCreditors(Entity entity, User user, String ticket) throws Exception{

        String username= user.getName();
        String password = user.getPassword();

        try{
            String dataSend = ticket;


            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getCreditors/").openConnection();
            connection.setRequestMethod("PUT");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }

    }

    public static void putExpense(Entity entity, User user, String ticket) throws Exception{

        String username= user.getName();
        String password = user.getPassword();

        try{
            String dataSend = ticket;


            dataSend = Encrypt.encrypt(key,dataSend);

            HttpURLConnection connection = (HttpURLConnection) new URL(endpoint+"getExpenses/").openConnection();
            connection.setRequestMethod("PUT");

            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(dataSend.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream response = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(response));
            StringBuilder data = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                data.append(line);
                data.append('\r');
            }
            rd.close();
        }
        catch (MalformedURLException mue){
            throw new NoUserFoundException();
        }
        catch (FileNotFoundException ioe){
            throw new FileNotFoundException(ioe.getMessage());
        }

        catch (Exception ioe){
            throw new Exception(ioe.getMessage());
        }

    }


}
