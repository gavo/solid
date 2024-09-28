package soe.mdeis.m7.solid.dto;

public class ApiResponse<T> {
   private final T data;
   private final String message;

   private ApiResponse(T data, String message) {
      this.data = data;
      this.message = message;
   }

   public static <S> ApiResponse<S> of(S data, String message) {
      return new ApiResponse<>(data, message);
   }

   public T getData() {
      return this.data;
   }

   public String getMessage() {
      return this.message;
   }

}