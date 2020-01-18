public class ResponseServiceImpl implements ResponseService {

    public Response createResponse(String message) {
        if (message != null) {
            Response response = new Response();
            response.setMessage(message);
            return response;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
