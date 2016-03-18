package no.westerdals.westbook.responses;

import lombok.*;
import no.westerdals.westbook.MessageConstant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultResponse<T>
{
    private static final String OK_STATUS = "OK";
    private static final String ERROR_STATUS = "ERROR";

    private String status;
    private String message;
    private Object extra;

    public static <T>ResultResponse<T> newOkResult(MessageConstant message)
    {
        return new ResultResponse<>(OK_STATUS, message.name(), null);
    }

    public static <T>ResultResponse<T> newOkResult(MessageConstant message, T extra)
    {
        return new ResultResponse<>(OK_STATUS, message.name(), extra);
    }

    public static <T>ResultResponse<T> newErrorResult(MessageConstant message)
    {
        return new ResultResponse<>(ERROR_STATUS, message.name(), null);
    }

    public static <T>ResultResponse<T> newErrorResult(MessageConstant message, T errorId)
    {
        return new ResultResponse<>(ERROR_STATUS, message.name(), errorId);
    }
}
