package no.westerdals.westbook.responses;

import lombok.*;
import no.westerdals.westbook.MessageConstant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultResponse
{
    private static final String OK_STATUS = "OK";
    private static final String ERROR_STATUS = "ERROR";

    private String status;
    private String message;
    private Object extra;

    public static ResultResponse newOkResult(MessageConstant message)
    {
        return new ResultResponse(OK_STATUS, message.name(), null);
    }

    public static ResultResponse newOkResult(MessageConstant message, Object extra)
    {
        return new ResultResponse(OK_STATUS, message.name(), extra);
    }

    public static ResultResponse newErrorResult(MessageConstant message)
    {
        return new ResultResponse(ERROR_STATUS, message.name(), null);
    }

    public static ResultResponse newErrorResult(MessageConstant message, String errorId)
    {
        return new ResultResponse(ERROR_STATUS, message.name(), errorId);
    }
}
