package com.company;
import pl.smsapi.BasicAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.ClientException;
import pl.smsapi.exception.SmsapiException;

public class SMSClass {





    public static void SendSms(String Number,String message) {
        try {
            String passwordHash = "token";
            BasicAuthClient client = new BasicAuthClient("email", passwordHash);

            SmsFactory smsApi = new SmsFactory(client);
            String phoneNumber = Number;
            SMSSend action = smsApi.actionSend()
                    .setText(message)
                    .setTo(phoneNumber);

            StatusResponse result = action.execute();

            for (MessageResponse status : result.getList() ) {
                System.out.println(status.getNumber() + " " + status.getStatus());
            }
        } catch (ClientException e) {
            /**
             * 101 Niepoprawne lub brak danych autoryzacji.
             * 102 Nieprawidłowy login lub hasło
             * 103 Brak punków dla tego użytkownika
             * 105 Błędny adres IP
             * 110 Usługa nie jest dostępna na danym koncie
             * 1000 Akcja dostępna tylko dla użytkownika głównego
             * 1001 Nieprawidłowa akcja
             */
            e.printStackTrace();
        } catch (SmsapiException e) {
            e.printStackTrace();
        }
    }
}