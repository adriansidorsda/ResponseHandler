import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ResponseHandlerTest {


    @Mock
    ResponseService responseService;

    private ResponseHandler responseHandler;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;


    @Before
    public void setup() {
        responseHandler = new ResponseHandler(responseService);
    }

    @Test
    public void verifyIfCreateResponseWasInvokedOnce() {
        //given

        //when
        responseHandler.doAction();
        //then
        verify(responseService, times(1)).createResponse(anyString());
    }

    @Test
    public void verifyIfCreateResponseWasInvokedAtLeastOnce() {
        //given

        //when
        responseHandler.doAction();
        //then
        verify(responseService, atLeast(1)).createResponse(stringArgumentCaptor.capture());

        assertEquals("success", stringArgumentCaptor.getValue());
    }

    @Test
    public void verifyResponseMessage() {
        //given
        Response response = new Response();
        response.setMessage("jakas wiadomosc");
        when(responseService.createResponse(anyString())).thenReturn(response);
        //when
        Response result = responseHandler.doAction();
        //then
        assertEquals("jakas wiadomosc", result.getMessage());

    }

    @Test
    public void testMethodWhenExceptionWasThrown() {
        //given
        when(responseService.createResponse(anyString())).thenThrow(IllegalArgumentException.class);
        //when
       Response result =  responseHandler.doAction();
        //then
        assertEquals("wyjatek", result.getMessage());
    }
}

//    public Response doAction() {
//        try {
//                Response response = responseService.createResponse("success");
//                return response;
//                } catch (IllegalArgumentException e) {
//                Response response = new Response();
//                response.setMessage("wyjatek");
//                return response;
//                }
//                }