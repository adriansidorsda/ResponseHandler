public class ResponseHandler {

    private ResponseService responseService;

    public ResponseHandler(ResponseService responseService) {
        this.responseService = responseService;
    }

    public Response doAction() {
        try {
            Response response = responseService.createResponse("success");
            return response;
        } catch (IllegalArgumentException e) {
            Response response = new Response();
            response.setMessage("wyjatek");
            return response;
        }
    }

}
