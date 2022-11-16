package Utilis;

import com.google.gson.Gson;

/*    */ 
/*    */ 
/*    */ 
/*    */ public class JsonHelper
/*    */ {
/*    */   public static <T> T JsonToClass(String json, Class<T> clazz) {
/*  8 */     Gson gson = new Gson();
/*    */     
/* 10 */     String jsonweb = json.replace("<", "{").replace(">", "}").replace("(", "[").replace(")", "]");
/*    */     
/* 12 */     return (T)gson.fromJson(json, clazz);
/*    */   }
/*    */   
/*    */   public static String ClassToJson(Object clazz) {
/* 16 */     Gson gson = new Gson();
/*    */     
/* 18 */     String json = gson.toJson(clazz);
/*    */     
/* 20 */     String jsonweb = json.replace("{", "<").replace("}", ">").replace("[", "(").replace("]", ")");
/*    */     
/* 22 */     return json;
/*    */   }
/*    */ }


/* Location:              C:\Users\jonte\Desktop\AccountSystem\accountbackend.jar!\BOOT-INF\classes\com\jojo\accountbackend\libary\JsonHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */