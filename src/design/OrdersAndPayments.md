# מערכת הזמנות ותשלומים -REST API

---

## 1. מחלקות מוצעות

### **Order**
```java
public class Order {
    private Long id;
    private LocalDate orderDate;
    private List<OrderItem> items;
    private double totalAmount;
    private boolean paid;
}
```
#### אחריות המחלקה:
-ניהול פרטי ההזמנה והפריטים שבה 
-חישוב סכום כולל של ההזמנה (totalAmount)
-מעקב אחר מצב תשלום (paid)
-אחראית על רשימת פריטים (items) שמחוברים למוצרים
-Single Responsibility: המחלקה אחראית רק על ניהול הזמנה
-Integrity: מאפשרת שמירה על קשר ברור עם פריטים (OrderItem)

### **OrderItem**
```java
public class OrderItem {
    private Long id;
    private Product product;
    private int quantity;
    private double price;
}
```
#### אחריות המחלקה:
-אחראית על פרטי מוצר ספציפי בהזמנה (כמות ומחיר)
-מאפשרת חישוב סכום פריט נפרד 
-מחברת בין Order לבין Product
-מחלקה קטנה, ממוקדת ואחראית רק על פריט בהזמנה
-מאפשרת הרחבה עתידית, לדוג הוספת הנחות או מבצעים לפריט

### **Payment**
```java
public class Payment {
    private Long id;
    private Long orderId;        // Foreign Key – מזהה ההזמנה
    private double amount;
    private LocalDate paymentDate;
    private String method;       // סוג תשלום (כרטיס אשראי, PayPal וכו')
}
```
#### אחריות המחלקה:
-תיעוד כל תשלום שבוצע
-קישור להזמנה ספציפית באמצעות orderId
-שמירה על סכום, תאריך ואמצעי תשלום
-מבטיח תקינות קשרים עם ההזמנה
-מאפשר בדיקות נוספות (סכום תשלום, תשלום כפול)

### **Product**
```java
public class Product {
    private Long id;
    private String name;
    private double price;
}
```
#### אחריות המחלקה:
-אחראית על מידע בסיסי של מוצר (שם ומחיר)
-מאפשרת חיבור בין OrderItem למוצר
-קטנה וברורה, מאפשרת הרחבה עתידית (תיאור מוצר, קטגוריה, מלאי)

## 2. איך לוודא שתשלום קשור להזמנה נכונה
בדיקה בקוד לפני יצירת Payment 

```java
public Payment createPayment(Payment payment) {
    if (!orderRepository.existsById(payment.getOrderId())) {
        throw new IllegalArgumentException("Order not found");
    }
    return paymentRepository.save(payment);
}
```
-מוודא שההזמנה קיימת לפני שמוסיפים תשלום
-מונע תשלומים עבור הזמנות לא קיימות

- Constraint- הייתי מוסיפה בדיקה גם ברמת בסיס הנתונים

```sql
FOREIGN KEY (order_id) REFERENCES orders(id)
```
-order_id ב-Payments חייב להתאים ל-id בטבלת Orders
-מבטיח תקינות נתונים גם אם מישהו מנסה לעקוף את ה-API

####בדיקות נוספות  
-סכום התשלום חייב להתאים לסכום ההזמנה
-אין אפשרות לבצע תשלום כפול על אותה הזמנה

