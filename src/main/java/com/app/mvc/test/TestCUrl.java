package com.app.mvc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class TestCUrl {

    public static String SendRequestToUrl(String requestContent, String urlString, String paraName) {
        InputStream errorStream = null;
        HttpURLConnection httpCon = null;
        try {
            URL url = new URL(urlString);
            String content = XMLToString(requestContent);
            String soapMessage = AddSoapShell(content);
            System.out.println(soapMessage);
            httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("POST");
            httpCon.setDoOutput(true);
            httpCon.setDoInput(true);

            httpCon.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            httpCon.setRequestProperty("SOAPAction", "http://ctrip.com/Request");
            httpCon.setRequestProperty("Host", "openapi.ctrip.com");
            httpCon.setRequestProperty("Accept-Encoding", "gzip,deflate");
            httpCon.setRequestProperty("Content-Length", String.valueOf(soapMessage.length()));

            OutputStream outputStream = httpCon.getOutputStream();
            outputStream.write(soapMessage.getBytes("UTF-8"));
            outputStream.close();

            InputStream inputStream = httpCon.getInputStream();
            BufferedReader br = null;
            if (httpCon.getRequestProperty("Accept-Encoding") != null) {
                try {
                    GZIPInputStream gzipStream = new GZIPInputStream(inputStream);
                    br = new BufferedReader(new InputStreamReader(gzipStream, "UTF-8"));
                } catch (IOException e) {
                    br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                }
            } else {
                br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            }
            StringBuffer result = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            return StringToXML(RemoveSoapShell(result.toString()));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            errorStream = httpCon.getErrorStream();
            if (errorStream != null) {
                String errorMessage = null;
                String line = null;
                BufferedReader br = new BufferedReader(new InputStreamReader(errorStream));
                try {
                    while ((line = br.readLine()) != null) {
                        errorMessage += line;
                    }
                    return errorMessage;
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            try {
                errorStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpCon != null) {
                httpCon.disconnect();
            }
        }

        return "";
    }

    private static String AddSoapShell(String patameterValue) throws Exception {
        String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><Request xmlns=\"http://ctrip.com/\"><requestXML>string</requestXML></Request></soap:Body></soap:Envelope>";
        return result.replaceAll("string", patameterValue);
    }

    private static String RemoveSoapShell(String source) {
        String result = "";
        int indexElementBegin = source.indexOf("<RequestResult>");
        int indexElementEnd = source.indexOf("</RequestResult>");
        if (indexElementBegin > 0 && indexElementEnd > 0) {
            result = source.substring(indexElementBegin + "<RequestResult>".length(), indexElementEnd);
        }
        return result;
    }

    private static String XMLToString(String source) {
        String result = source.replaceAll("<", "&lt;");
        result = result.replaceAll(">", "&gt;");
        return result;
    }

    private static String StringToXML(String source) {
        String result = source.replaceAll("&lt;", "<");
        result = result.replaceAll("&gt;", ">");
        return result;
    }

    public static void main(String[] args) throws Exception {
        ping();
    }

    private static void hotelDes() {
        String request = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1452849794238\" RequestType=\"OTA_HotelDescriptiveInfo\" Signature=\"EC72C4E645B1C615FCD4A1EE82AC3FE1\" /><HotelRequest><RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><OTA_HotelDescriptiveInfoRQ Version=\"1.0\" xsi:schemaLocation=\"http://www.opentravel.org/OTA/2003/05 OTA_HotelDescriptiveInfoRQ.xsd\" xmlns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><HotelDescriptiveInfos><HotelDescriptiveInfo HotelCode=\"2497609\"><HotelInfo SendData=\"true\" /><FacilityInfo SendGuestRooms=\"true\" /><AreaInfo SendAttractions=\"false\" SendRecreations=\"false\" /><MultimediaObjects SendData=\"false\" /></HotelDescriptiveInfo></HotelDescriptiveInfos></OTA_HotelDescriptiveInfoRQ></RequestBody></HotelRequest></Request>";
        String url = "http://openapi.ctrip.com/Hotel/OTA_HotelDescriptiveInfo.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    public static String fetchPrice(String checkin, String checkout, String hotelId) {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1457075808246\" RequestType=\"OTA_HotelRatePlan\" Signature=\"D649A8F17FDDA2857692A83AA7048C6F\"/>";
        String request = "<HotelRequest><RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><ns:OTA_HotelRatePlanRQ TimeStamp=\"2016-03-04T15:00:00.000+08:00\" Version=\"3.0\"><ns:RatePlans><ns:RatePlan><ns:DateRange Start=\"%s\" End=\"%s\" /><ns:RatePlanCandidates><ns:RatePlanCandidate AvailRatesOnlyInd=\"true\" IsCNYCurrency=\"true\"><ns:HotelRefs><ns:HotelRef HotelCode=\"%s\" /></ns:HotelRefs></ns:RatePlanCandidate></ns:RatePlanCandidates><ns:TPA_Extensions RestrictedDisplayIndicator=\"true\" /></ns:RatePlan></ns:RatePlans></ns:OTA_HotelRatePlanRQ></RequestBody></HotelRequest>";
        request = request.format(request, checkin, checkout, hotelId);
        request = header + request + "</Request>";
        String url = "http://openapi.ctrip.com/Hotel/OTA_HotelRatePlan.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(request);
        System.out.println(response);
        return response;
    }

    private static void hotelAvail() {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1453192976194\" RequestType=\"OTA_HotelAvail\" Signature=\"90EC083037D976E45ADF39C368540C95\"/>";
        String request = "<HotelRequest><RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><ns:OTA_HotelAvailRQ Version=\"2.0\" TimeStamp=\"2016-02-15T00:00:00.000+08:00\"><ns:AvailRequestSegments><ns:AvailRequestSegment><ns:HotelSearchCriteria><ns:Criterion><ns:HotelRef HotelCode=\"717784\" /><ns:StayDateRange Start=\"2016-04-25T13:00:00.000+08:00\" End=\"2016-04-26T15:00:00.000+08:00\" /><ns:RatePlanCandidates><ns:RatePlanCandidate RatePlanCode=\"20544696\" RatePlanID=\"1\" RatePlanCategory=\"501\" /></ns:RatePlanCandidates><ns:RoomStayCandidates><ns:RoomStayCandidate Quantity=\"1\" BookingCode=\"5106507107304\"><ns:GuestCounts IsPerRoom=\"false\"><ns:GuestCount Count=\"2\" /></ns:GuestCounts></ns:RoomStayCandidate></ns:RoomStayCandidates><ns:TPA_Extensions><ns:LateArrivalTime>2016-04-26T06:00:00.000+08:00</ns:LateArrivalTime></ns:TPA_Extensions></ns:Criterion></ns:HotelSearchCriteria></ns:AvailRequestSegment></ns:AvailRequestSegments></ns:OTA_HotelAvailRQ></RequestBody></HotelRequest>";
        request = header + request + "</Request>";
        String url = "http://openapi.ctrip.com/Hotel/OTA_HotelAvail.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    private static void hotelAvailBookingCode() {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1453192976194\" RequestType=\"OTA_HotelAvail\" Signature=\"90EC083037D976E45ADF39C368540C95\"/>";
        String request = "<HotelRequest><RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><ns:OTA_HotelAvailRQ Version=\"2.0\" TimeStamp=\"2016-02-15T00:00:00.000+08:00\"><ns:AvailRequestSegments><ns:AvailRequestSegment><ns:HotelSearchCriteria><ns:Criterion><ns:HotelRef HotelCode=\"998521\" /><ns:StayDateRange Start=\"2016-03-19T13:00:00.000+08:00\" End=\"2016-03-22T15:00:00.000+08:00\" /><ns:RatePlanCandidates><ns:RatePlanCandidate RatePlanCode=\"13482650\" RatePlanCategory=\"501\" /></ns:RatePlanCandidates><ns:RoomStayCandidates><ns:RoomStayCandidate Quantity=\"1\"><ns:GuestCounts IsPerRoom=\"true\"><ns:GuestCount Count=\"3\" /></ns:GuestCounts></ns:RoomStayCandidate></ns:RoomStayCandidates><ns:TPA_Extensions><ns:LateArrivalTime>2016-03-20T06:00:00.000+08:00</ns:LateArrivalTime></ns:TPA_Extensions></ns:Criterion></ns:HotelSearchCriteria></ns:AvailRequestSegment></ns:AvailRequestSegments></ns:OTA_HotelAvailRQ></RequestBody></HotelRequest>";
        request = header + request + "</Request>";
        String url = "http://openapi.ctrip.com/Hotel/OTA_HotelAvail.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    private static void userUniqueID() {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1456031215977\" RequestType=\"OTA_UserUniqueID\" Signature=\"6E925951ED7ACAAB6C620DC559A18AFB\" />";
        String request = "<UserRequest><AllianceID>108284</AllianceID><SID>774665</SID><UidKey>8A78B8F6-C122-48DD-835B-D8F82257DA0D</UidKey></UserRequest>";
        request = header + request + "</Request>";
        String url = "http://openapi.ctrip.com/Hotel/OTA_UserUniqueID.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    private static void hotelResSave() {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1456031379307\" RequestType=\"OTA_HotelResSave\" Signature=\"5FB9E1177426FB794977D5B32D97B89C\"/>";
        String request = "<HotelRequest><RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><ns:OTA_HotelResSaveRQ TimeStamp=\"2016-02-23T11:00:57+08:00\" Version=\"1.0\" PrimaryLangID=\"zh-CN\" xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\"><ns:UniqueID Type=\"28\" ID=\"108284\" /><ns:UniqueID Type=\"503\" ID=\"774665\" /><ns:UniqueID Type=\"504\" ID=\"100721974781\" /><ns:UniqueID Type=\"1\" ID=\"e818c10a-de10-472c-b957-fa2c54e2329c\" /><ns:HotelReservations><ns:HotelReservation><ns:RoomStays><ns:RoomStay><ns:RoomTypes><ns:RoomType NumberOfUnits=\"1\" /></ns:RoomTypes><ns:RatePlans><ns:RatePlan RatePlanCode=\"4549428\" RatePlanCategory=\"501\" /></ns:RatePlans><ns:BasicPropertyInfo HotelCode=\"998521\" /></ns:RoomStay></ns:RoomStays><ns:ResGuests><ns:ResGuest><ns:Profiles><ns:ProfileInfo><ns:Profile><ns:Customer><ns:PersonName><ns:Surname>li/jian</ns:Surname></ns:PersonName><ns:ContactPerson ContactType=\"sms\"><ns:PersonName><ns:Surname>li/jian</ns:Surname></ns:PersonName><ns:Telephone PhoneNumber=\"11110101234\" PhoneTechType=\"5\" /><ns:Email>jiankang.li@qunar.com</ns:Email></ns:ContactPerson></ns:Customer></ns:Profile></ns:ProfileInfo></ns:Profiles><ns:TPA_Extensions><ns:LateArrivalTime>2016-03-21T14:00:00.000+08:00</ns:LateArrivalTime></ns:TPA_Extensions></ns:ResGuest></ns:ResGuests><ns:ResGlobalInfo><ns:GuestCounts IsPerRoom=\"false\"><ns:GuestCount Count=\"1\" /></ns:GuestCounts><ns:TimeSpan Start=\"2016-03-21T23:59:59.000+08:00\" End=\"2016-03-22T12:00:00.000+08:00\" /><ns:Total AmountBeforeTax=\"815.54\" CurrencyCode=\"CNY\" /></ns:ResGlobalInfo></ns:HotelReservation></ns:HotelReservations></ns:OTA_HotelResSaveRQ></RequestBody></HotelRequest>";
        request = header + request + "</Request>";
        String url = "http://openapi.ctrip.com/Hotel/OTA_HotelResSave.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    private static void hotelResSubmit() {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1453282872142\" RequestType=\"OTA_HotelResSubmit\" Signature=\"53200A637FDCBE9206BF9E9251D7BAEC\"/>";
        String request = "<HotelRequest><RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><ns:OTA_HotelResSubmitRQ TimeStamp=\"2016-01-19T20:00:15+08:00\" Version=\"1.0\" PrimaryLangID=\"zh-CN\" xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\"><ns:ReservationPayment><ns:ReservationID Type=\"501\" ID=\"1738217181\" /><ns:PaymentDetail PaymentType=\"9\" GuaranteeIndicator=\"false\"><ns:ChannelAccount ChannelAccountIndicator=\"true\" /><ns:PaymentAmount CurrencyCode=\"CNY\" Amount=\"856.00\" /></ns:PaymentDetail></ns:ReservationPayment></ns:OTA_HotelResSubmitRQ></RequestBody></HotelRequest>";
        request = header + request + "</Request>";
        String url = "http://openapi.ctrip.com/Hotel/OTA_HotelResSubmit.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    public static void cancel(String orderNum) {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1453367807435\" RequestType=\"OTA_Cancel\" Signature=\"EB946162C2900B80768F9AE097CAD414\"/>";
        String request = "<HotelRequest><RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><ns:OTA_CancelRQ TimeStamp=\"2016-01-21T13:00:06+08:00\" Version=\"1.0\" xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\"><ns:UniqueID Type=\"28\" ID=\"108284\" /><ns:UniqueID Type=\"503\" ID=\"774665\" /><ns:UniqueID Type=\"1\" ID=\"e818c10a-de10-472c-b957-fa2c54e2329c\" /><ns:UniqueID Type=\"501\" ID=\"%s\" /><ns:Reasons><ns:Reason Type=\"509\" /></ns:Reasons></ns:OTA_CancelRQ></RequestBody></HotelRequest>";
        request = header + request + "</Request>";
        request = String.format(request, orderNum);
        String url = "http://openapi.ctrip.com/Hotel/OTA_Cancel.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    private static void hotelResNotif() {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1453367875576\" RequestType=\"OTA_HotelResNotif\" Signature=\"8D1456890A48334C47D7F60FF60B43B7\"/>";
        String request = "<HotelRequest xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\"><RequestBody><ns:OTA_HotelResNotifRQ Version=\"1.0\"><ns:HotelReservations><ns:HotelReservation LastModifyDateTime=\"2016-03-04T18:30:20+08:00\"><ns:UniqueID Type=\"28\" ID=\"108284\"/><ns:UniqueID Type=\"503\" ID=\"774665\"/></ns:HotelReservation></ns:HotelReservations></ns:OTA_HotelResNotifRQ></RequestBody></HotelRequest>";
        request = header + request + "</Request>";
        String url = "http://openapi.ctrip.com/Hotel/OTA_HotelResNotif.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    private static void read(String orderNum) {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"1453369100448\" RequestType=\"OTA_Read\" Signature=\"2E6BD84DB14573BE1DDFE54343438933\"/>";
        String request = "<HotelRequest xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\"><RequestBody><ns:OTA_ReadRQ Version=\"2.0\"><ns:UniqueID Type=\"28\" ID=\"108284\" /><ns:UniqueID Type=\"503\" ID=\"774665\" /><ns:UniqueID Type=\"1\" ID=\"e818c10a-de10-472c-b957-fa2c54e2329c\" /><ns:UniqueID Type=\"501\" ID=\"%s\" /></ns:OTA_ReadRQ></RequestBody></HotelRequest>";
        request = header + request + "</Request>";
        request = String.format(request, orderNum);
        System.out.println(request);
        String url = "http://openapi.ctrip.com/Hotel/OTA_Read.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    private static void readC(String orderNum) {
        String header = "<Request><Header AllianceID=\"108284\" SID=\"552086\" TimeStamp=\"1461211392119\" RequestType=\"OTA_Read\" Signature=\"294FDCB1E3FDA242E775F9902A385416\"/>";
        String request = "<HotelRequest xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\"><RequestBody><ns:OTA_ReadRQ Version=\"2.0\"><ns:UniqueID Type=\"28\" ID=\"108284\" /><ns:UniqueID Type=\"503\" ID=\"552086\" /><ns:UniqueID Type=\"1\" ID=\"caa1a3a8-af36-484a-96fb-c29a305c453d\" /><ns:UniqueID Type=\"501\" ID=\"%s\" /></ns:OTA_ReadRQ></RequestBody></HotelRequest>";
        request = header + request + "</Request>";
        request = String.format(request, orderNum);
        System.out.println(request);
        String url = "http://openapi.ctrip.com/Hotel/OTA_Read.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(response);
    }

    private static void ping() throws Exception {
        long timestamp = System.currentTimeMillis()/1000;
        String request =
                "<Request><Header AllianceID=\"108284\" SID=\"774665\" TimeStamp=\"" + timestamp + "\" RequestType=\" OTA_Ping\" Signature=\""
                        + SignatureUtils.CalculationSignature(timestamp + "", "108284", "8A78B8F6-C122-48DD-835B-D8F82257DA0D", "774665", " OTA_Ping") + "\" />"
                        + "<HotelRequest><RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
                        + "<ns:OTA_PingRQ><ns:EchoData>test</ns:EchoData></ns:OTA_PingRQ></RequestBody></HotelRequest></Request>";
        String url = "http://openapi.ctrip.com/Hotel/OTA_Ping.asmx";
        TestCUrl testCUrl = new TestCUrl();
        String response = testCUrl.SendRequestToUrl(request, url, "");
        System.out.println(request +"\n"+response);
    }

}