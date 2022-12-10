package xyz.silkdog.messagequeue.code;

public enum ServiceCode {
    General("hello"),
    Marketing("marketing");

    private final String service;

    ServiceCode(String service) {
        this.service = service;
    }

    /** `service`를 통해서 `ServiceCode`를 조회한다. */
    public static ServiceCode getService(String find) {
        for (ServiceCode sc : ServiceCode.values()) {
            if (sc.service.equals(find)) {
                return sc;
            }
        }
        return null;
    }

}
