package com.suiteonix.schoolpaz.kernel.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class Ex {

    public static PazException badRequest(String title, String detail) {
        return new PazException(HttpStatus.BAD_REQUEST)
                .title(title != null ? title : "Bad Request")
                .detail(detail != null ? detail : "The request was invalid or cannot be processed");
    }

    public static PazException badRequest(String title) {
        return badRequest(title, null);
    }

    public static PazException unauthorized(String title, String detail) {
        return new PazException(HttpStatus.UNAUTHORIZED)
                .title(title != null ? title : "Unauthorized")
                .detail(detail != null ? detail : "Authentication is required and has failed or has not been provided");
    }

    public static PazException unauthorized(String title) {
        return unauthorized(title, null);
    }

    public static PazException forbidden(String title, String detail) {
        return new PazException(HttpStatus.FORBIDDEN)
                .title(title != null ? title : "Forbidden")
                .detail(detail != null ? detail : "Access to the requested resource is forbidden");
    }

    public static PazException forbidden(String title) {
        return forbidden(title, null);
    }

    public static PazException notFound(String title, String detail) {
        return new PazException(HttpStatus.NOT_FOUND)
                .title(title != null ? title : "Not Found")
                .detail(detail != null ? detail : "The requested resource was not found");
    }

    public static PazException notFound(String title) {
        return notFound(Objects.nonNull(title) ? title : "NOT_FOUND", null);
    }

    public static PazException methodNotAllowed(String title, String detail) {
        return new PazException(HttpStatus.METHOD_NOT_ALLOWED)
                .title(title != null ? title : "Method Not Allowed")
                .detail(detail != null ? detail : "The requested HTTP method is not supported for this resource");
    }

    public static PazException methodNotAllowed(String title) {
        return methodNotAllowed(title, null);
    }

    public static PazException conflict(String title, String detail) {
        return new PazException(HttpStatus.CONFLICT)
                .title(title != null ? title : "Conflict")
                .detail(detail != null ? detail : "The request conflicts with the current state of the target resource");
    }

    public static PazException conflict(String title) {
        return conflict(title, null);
    }

    public static PazException internalServerError(String title, String detail) {
        return new PazException(HttpStatus.INTERNAL_SERVER_ERROR)
                .title(title != null ? title : "Internal Server Error")
                .detail(detail != null ? detail : "An unexpected error occurred while processing the request");
    }

    public static PazException internalServerError(String title) {
        return internalServerError(title, null);
    }

    public static PazException serviceUnavailable(String title, String detail) {
        return new PazException(HttpStatus.SERVICE_UNAVAILABLE)
                .title(title != null ? title : "Service Unavailable")
                .detail(detail != null ? detail : "The service is temporarily unavailable");
    }

    public static PazException serviceUnavailable(String title) {
        return serviceUnavailable(title, null);
    }

    // 1xx Informational

    public static PazException continue100(String title, String detail) {
        return new PazException(HttpStatus.CONTINUE)
                .title(title != null ? title : "Continue")
                .detail(detail != null ? detail : "The server has received the request headers and the client should proceed to send the request body");
    }

    public static PazException continue100(String title) {
        return continue100(title, null);
    }

    public static PazException switchingProtocols(String title, String detail) {
        return new PazException(HttpStatus.SWITCHING_PROTOCOLS)
                .title(title != null ? title : "Switching Protocols")
                .detail(detail != null ? detail : "The server is switching protocols according to the client's request");
    }

    public static PazException switchingProtocols(String title) {
        return switchingProtocols(title, null);
    }

    public static PazException processing(String title, String detail) {
        return new PazException(HttpStatus.PROCESSING)
                .title(title != null ? title : "Processing")
                .detail(detail != null ? detail : "The server is processing the request but no response is available yet");
    }

    public static PazException processing(String title) {
        return processing(title, null);
    }

//    public static NixException checkpoint (String title, String detail) {
//        return new NixException(HttpStatus.CHECKPOINT)
//                .title(title != null ? title : "Checkpoint")
//                .detail(detail != null ? detail: "The request should be retried after performing the appropriate action");
//    }

    // 2xx Success

    public static PazException ok(String title, String detail) {
        return new PazException(HttpStatus.OK)
                .title(title != null ? title : "OK")
                .detail(detail != null ? detail : "The request has succeeded");
    }

    public static PazException ok(String title) {
        return ok(title, null);
    }

    public static PazException created(String title, String detail) {
        return new PazException(HttpStatus.CREATED)
                .title(title != null ? title : "Created")
                .detail(detail != null ? detail : "The request has been fulfilled and a new resource has been created");
    }

    public static PazException created(String title) {
        return created(title, null);
    }

    public static PazException accepted(String title, String detail) {
        return new PazException(HttpStatus.ACCEPTED)
                .title(title != null ? title : "Accepted")
                .detail(detail != null ? detail : "The request has been accepted for processing, but the processing has not been completed");
    }

    public static PazException accepted(String title) {
        return accepted(title, null);
    }

    public static PazException nonAuthoritativeInformation(String title, String detail) {
        return new PazException(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
                .title(title != null ? title : "Non-Authoritative Information")
                .detail(detail != null ? detail : "The server is a transforming proxy that received a 200 OK from its origin but is returning a modified version of the origin's response");
    }

    public static PazException nonAuthoritativeInformation(String title) {
        return nonAuthoritativeInformation(title, null);
    }

    public static PazException noContent(String title, String detail) {
        return new PazException(HttpStatus.NO_CONTENT)
                .title(title != null ? title : "No Content")
                .detail(detail != null ? detail : "The server successfully processed the request but is not returning any content");
    }

    public static PazException noContent(String title) {
        return noContent(title, null);
    }

    public static PazException resetContent(String title, String detail) {
        return new PazException(HttpStatus.RESET_CONTENT)
                .title(title != null ? title : "Reset Content")
                .detail(detail != null ? detail : "The server successfully processed the request, but is not returning any content and requires that the requester reset the document view");
    }

    public static PazException resetContent(String title) {
        return resetContent(title, null);
    }

    public static PazException partialContent(String title, String detail) {
        return new PazException(HttpStatus.PARTIAL_CONTENT)
                .title(title != null ? title : "Partial Content")
                .detail(detail != null ? detail : "The server is delivering only part of the resource due to a range header sent by the client");
    }

    public static PazException partialContent(String title) {
        return partialContent(title, null);
    }

    public static PazException multiStatus(String title, String detail) {
        return new PazException(HttpStatus.MULTI_STATUS)
                .title(title != null ? title : "Multi-Status")
                .detail(detail != null ? detail : "The message body that follows is an XML message and can contain a number of separate response codes");
    }

    public static PazException multiStatus(String title) {
        return multiStatus(title, null);
    }

    public static PazException alreadyReported(String title, String detail) {
        return new PazException(HttpStatus.ALREADY_REPORTED)
                .title(title != null ? title : "Already Reported")
                .detail(detail != null ? detail : "The members of a DAV binding have already been enumerated in a previous reply to this request");
    }

    public static PazException alreadyReported(String title) {
        return alreadyReported(title, null);
    }

    public static PazException imUsed(String title, String detail) {
        return new PazException(HttpStatus.IM_USED)
                .title(title != null ? title : "IM Used")
                .detail(detail != null ? detail : "The server has fulfilled a request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance");
    }

    public static PazException imUsed(String title) {
        return imUsed(title, null);
    }

    // 3xx Redirection

    public static PazException multipleChoices(String title, String detail) {
        return new PazException(HttpStatus.MULTIPLE_CHOICES)
                .title(title != null ? title : "Multiple Choices")
                .detail(detail != null ? detail : "The requested resource corresponds to any one of a set of representations, each with its own specific location");
    }

    public static PazException multipleChoices(String title) {
        return multipleChoices(title, null);
    }

    public static PazException movedPermanently(String title, String detail) {
        return new PazException(HttpStatus.MOVED_PERMANENTLY)
                .title(title != null ? title : "Moved Permanently")
                .detail(detail != null ? detail : "The requested resource has been assigned a new permanent URI");
    }

    public static PazException movedPermanently(String title) {
        return movedPermanently(title, null);
    }

    public static PazException found(String title, String detail) {
        return new PazException(HttpStatus.FOUND)
                .title(title != null ? title : "Found")
                .detail(detail != null ? detail : "The requested resource temporarily resides under a different URI");
    }

    public static PazException found(String title) {
        return found(title, null);
    }

    public static PazException seeOther(String title, String detail) {
        return new PazException(HttpStatus.SEE_OTHER)
                .title(title != null ? title : "See Other")
                .detail(detail != null ? detail : "The response to the request can be found under a different URI");
    }

    public static PazException seeOther(String title) {
        return seeOther(title, null);
    }

    public static PazException notModified(String title, String detail) {
        return new PazException(HttpStatus.NOT_MODIFIED)
                .title(title != null ? title : "Not Modified")
                .detail(detail != null ? detail : "The resource has not been modified since the version specified by the request headers");
    }

    public static PazException notModified(String title) {
        return notModified(title, null);
    }

//    public static NixException useProxy(String title, String detail) {
//        return new NixException(HttpStatus.USE_PROXY)
//                .title(title != null ? title : "Use Proxy")
//                .detail(detail != null ? detail: "The requested resource must be accessed through the proxy given by the Location field");
//    }

    public static PazException temporaryRedirect(String title, String detail) {
        return new PazException(HttpStatus.TEMPORARY_REDIRECT)
                .title(title != null ? title : "Temporary Redirect")
                .detail(detail != null ? detail : "The requested resource resides temporarily under a different URI");
    }

    public static PazException temporaryRedirect(String title) {
        return temporaryRedirect(title, null);
    }

    public static PazException permanentRedirect(String title, String detail) {
        return new PazException(HttpStatus.PERMANENT_REDIRECT)
                .title(title != null ? title : "Permanent Redirect")
                .detail(detail != null ? detail : "The requested resource has been assigned a new permanent URI");
    }

    public static PazException permanentRedirect(String title) {
        return permanentRedirect(title, null);
    }

    // 4xx Client Error - Some already implemented above

    public static PazException payloadTooLarge(String title, String detail) {
        return new PazException(HttpStatus.PAYLOAD_TOO_LARGE)
                .title(title != null ? title : "Payload Too Large")
                .detail(detail != null ? detail : "The server is refusing to process a request because the request payload is too large");
    }

    public static PazException payloadTooLarge(String title) {
        return payloadTooLarge(title, null);
    }

    public static PazException uriTooLong(String title, String detail) {
        return new PazException(HttpStatus.URI_TOO_LONG)
                .title(title != null ? title : "URI Too Long")
                .detail(detail != null ? detail : "The server is refusing to service the request because the request-target is too long");
    }

    public static PazException uriTooLong(String title) {
        return uriTooLong(title, null);
    }

    public static PazException unsupportedMediaType(String title, String detail) {
        return new PazException(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .title(title != null ? title : "Unsupported Media Type")
                .detail(detail != null ? detail : "The server is refusing to service the request because the entity of the request is in a format not supported by the requested resource");
    }

    public static PazException unsupportedMediaType(String title) {
        return unsupportedMediaType(title, null);
    }

    public static PazException requestedRangeNotSatisfiable(String title, String detail) {
        return new PazException(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
                .title(title != null ? title : "Requested Range Not Satisfiable")
                .detail(detail != null ? detail : "The client has asked for a portion of the file, but the server cannot supply that portion");
    }

    public static PazException requestedRangeNotSatisfiable(String title) {
        return requestedRangeNotSatisfiable(title, null);
    }

    public static PazException expectationFailed(String title, String detail) {
        return new PazException(HttpStatus.EXPECTATION_FAILED)
                .title(title != null ? title : "Expectation Failed")
                .detail(detail != null ? detail : "The server cannot meet the requirements of the Expect request-header field");
    }

    public static PazException expectationFailed(String title) {
        return expectationFailed(title, null);
    }

    public static PazException iAmATeapot(String title, String detail) {
        return new PazException(HttpStatus.I_AM_A_TEAPOT)
                .title(title != null ? title : "I'm a teapot")
                .detail(detail != null ? detail : "The server refuses to brew coffee because it is a teapot");
    }

    public static PazException iAmATeapot(String title) {
        return iAmATeapot(title, null);
    }

    public static PazException unprocessableEntity(String title, String detail) {
        return new PazException(HttpStatus.UNPROCESSABLE_ENTITY)
                .title(title != null ? title : "Unprocessable Entity")
                .detail(detail != null ? detail : "The server understands the content type of the request entity but was unable to process the contained instructions");
    }

    public static PazException unprocessableEntity(String title) {
        return unprocessableEntity(title, null);
    }

    public static PazException locked(String title, String detail) {
        return new PazException(HttpStatus.LOCKED)
                .title(title != null ? title : "Locked")
                .detail(detail != null ? detail : "The resource that is being accessed is locked");
    }

    public static PazException locked(String title) {
        return locked(title, null);
    }

    public static PazException failedDependency(String title, String detail) {
        return new PazException(HttpStatus.FAILED_DEPENDENCY)
                .title(title != null ? title : "Failed Dependency")
                .detail(detail != null ? detail : "The request failed due to failure of a previous request");
    }

    public static PazException failedDependency(String title) {
        return failedDependency(title, null);
    }

    public static PazException tooEarly(String title, String detail) {
        return new PazException(HttpStatus.TOO_EARLY)
                .title(title != null ? title : "Too Early")
                .detail(detail != null ? detail : "The server is unwilling to risk processing a request that might be replayed");
    }

    public static PazException tooEarly(String title) {
        return tooEarly(title, null);
    }

    public static PazException upgradeRequired(String title, String detail) {
        return new PazException(HttpStatus.UPGRADE_REQUIRED)
                .title(title != null ? title : "Upgrade Required")
                .detail(detail != null ? detail : "The client should switch to a different protocol");
    }

    public static PazException upgradeRequired(String title) {
        return upgradeRequired(title, null);
    }

    public static PazException preconditionRequired(String title, String detail) {
        return new PazException(HttpStatus.PRECONDITION_REQUIRED)
                .title(title != null ? title : "Precondition Required")
                .detail(detail != null ? detail : "The server requires the request to be conditional");
    }

    public static PazException preconditionRequired(String title) {
        return preconditionRequired(title, null);
    }

    public static PazException tooManyRequests(String title, String detail) {
        return new PazException(HttpStatus.TOO_MANY_REQUESTS)
                .title(title != null ? title : "Too Many Requests")
                .detail(detail != null ? detail : "The user has sent too many requests in a given amount of time");
    }

    public static PazException tooManyRequests(String title) {
        return tooManyRequests(title, null);
    }

    public static PazException requestHeaderFieldsTooLarge(String title, String detail) {
        return new PazException(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE)
                .title(title != null ? title : "Request Header Fields Too Large")
                .detail(detail != null ? detail : "The server is unwilling to process the request because its header fields are too large");
    }

    public static PazException requestHeaderFieldsTooLarge(String title) {
        return requestHeaderFieldsTooLarge(title, null);
    }

    public static PazException unavailableForLegalReasons(String title, String detail) {
        return new PazException(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                .title(title != null ? title : "Unavailable For Legal Reasons")
                .detail(detail != null ? detail : "The server is denying access to the resource as a consequence of a legal demand");
    }

    public static PazException unavailableForLegalReasons(String title) {
        return unavailableForLegalReasons(title, null);
    }

    // 5xx Server Error - Some already implemented above

    public static PazException notImplemented(String title, String detail) {
        return new PazException(HttpStatus.NOT_IMPLEMENTED)
                .title(title != null ? title : "Not Implemented")
                .detail(detail != null ? detail : "The server does not support the functionality required to fulfill the request");
    }

    public static PazException notImplemented(String title) {
        return notImplemented(title, null);
    }

    public static PazException badGateway(String title, String detail) {
        return new PazException(HttpStatus.BAD_GATEWAY)
                .title(title != null ? title : "Bad Gateway")
                .detail(detail != null ? detail : "The server, while acting as a gateway or proxy, received an invalid response from the upstream server");
    }

    public static PazException badGateway(String title) {
        return badGateway(title, null);
    }

    public static PazException gatewayTimeout(String title, String detail) {
        return new PazException(HttpStatus.GATEWAY_TIMEOUT)
                .title(title != null ? title : "Gateway Timeout")
                .detail(detail != null ? detail : "The server, while acting as a gateway or proxy, did not receive a timely response from the upstream server");
    }

    public static PazException gatewayTimeout(String title) {
        return gatewayTimeout(title, null);
    }

    public static PazException httpVersionNotSupported(String title, String detail) {
        return new PazException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
                .title(title != null ? title : "HTTP Version Not Supported")
                .detail(detail != null ? detail : "The server does not support the HTTP protocol version used in the request");
    }

    public static PazException httpVersionNotSupported(String title) {
        return httpVersionNotSupported(title, null);
    }

    public static PazException variantAlsoNegotiates(String title, String detail) {
        return new PazException(HttpStatus.VARIANT_ALSO_NEGOTIATES)
                .title(title != null ? title : "Variant Also Negotiates")
                .detail(detail != null ? detail : "The server has an internal configuration error: the chosen variant resource is configured to engage in transparent content negotiation itself");
    }

    public static PazException variantAlsoNegotiates(String title) {
        return variantAlsoNegotiates(title, null);
    }

    public static PazException insufficientStorage(String title, String detail) {
        return new PazException(HttpStatus.INSUFFICIENT_STORAGE)
                .title(title != null ? title : "Insufficient Storage")
                .detail(detail != null ? detail : "The server has an internal configuration error: the chosen variant resource is configured to engage in transparent content negotiation itself");
    }

    public static PazException insufficientStorage(String title) {
        return insufficientStorage(title, null);
    }

    public static PazException loopDetected(String title, String detail) {
        return new PazException(HttpStatus.LOOP_DETECTED)
                .title(title != null ? title : "Loop Detected")
                .detail(detail != null ? detail : "The server detected an infinite loop while processing the request");
    }

    public static PazException loopDetected(String title) {
        return loopDetected(title, null);
    }

    public static PazException bandwidthLimitExceeded(String title, String detail) {
        return new PazException(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
                .title(title != null ? title : "Bandwidth Limit Exceeded")
                .detail(detail != null ? detail : "The server has exceeded the bandwidth specified by the server administrator");
    }

    public static PazException bandwidthLimitExceeded(String title) {
        return bandwidthLimitExceeded(title, null);
    }

    public static PazException notExtended(String title, String detail) {
        return new PazException(HttpStatus.NOT_EXTENDED)
                .title(title != null ? title : "Not Extended")
                .detail(detail != null ? detail : "Further extensions to the request are required for the server to fulfill it");
    }

    public static PazException notExtended(String title) {
        return notExtended(title, null);
    }

    public static PazException networkAuthenticationRequired(String title, String detail) {
        return new PazException(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
                .title(title != null ? title : "Network Authentication Required")
                .detail(detail != null ? detail : "The client needs to authenticate to gain network access");
    }

    public static PazException networkAuthenticationRequired(String title) {
        return networkAuthenticationRequired(title, null);
    }
}
