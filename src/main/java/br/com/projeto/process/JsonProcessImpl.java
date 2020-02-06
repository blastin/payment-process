package br.com.projeto.process;

import br.com.projeto.payment.PaymentAdapter;
import br.com.projeto.payment.Payments;
import br.com.projeto.utils.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

final class JsonProcessImpl extends FileProcessImpl {

    private static final Gson GSON =
            new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (jsonElement, type, jsonDeserializationContext) -> DateUtil.rawStringToLocalDate(jsonElement.toString()))
                    .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (jsonElement, type, jsonDeserializationContext) -> DateUtil.rawStringToLocalTime(jsonElement.toString()))
                    .create();

    protected JsonProcessImpl(final Payments payments) {
        super(payments);
    }

    @Override
    public Collection<PaymentAdapter> process(final String rawStringFile) {
        return Arrays.stream(GSON.fromJson(rawStringFile, JsonPaymentAdapter[].class)).map(JsonPaymentAdapter::build).collect(Collectors.toList());
    }

    private static class JsonPaymentAdapter {

        @SerializedName("id_cliente")
        private Integer clientId;

        @SerializedName("pagamento")
        private BigDecimal value;

        @SerializedName("data")
        private String paymentDate;

        @SerializedName("horario")
        private String paymentTime;

        private JsonPaymentAdapter() {
        }

        PaymentAdapter build() {
            return new PaymentAdapter(clientId, value, DateUtil.rawStringToLocalDate(paymentDate), DateUtil.rawStringToLocalTime(paymentTime));
        }

    }

}
