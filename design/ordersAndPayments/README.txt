Orders & Payments - Class Design
Classes
Order - represents a customer order (items, total amount, status).

OrderItem - individual item in the order (product, quantity, price).

Customer - holds customer details (name, email, etc.).

Payment - stores payment information, linked to an order.

OrderService - handles basic order logic (create, update, calculate totals).

PaymentService - processes payments and validates them against orders.

Responsibilities
Order - stores order details and order status.

OrderItem - stores product info for each order line.

Customer - stores customer information.

Payment - stores payment details and links to Order.

OrderService - manages order creation, updates, and totals.

PaymentService - ensures payment is valid and updates order and payment status.

Ensuring Payment is Linked Correctly
Use orderId in the Payment class to link each payment to the correct order.

When creating a payment, validate that the order exists in the repository:

public Payment createPayment(Payment payment) {
    if (!orderRepository.existsById(payment.getOrderId())) {
        throw new IllegalArgumentException("Order not found");
    }
    return paymentRepository.save(payment);
}
Optionally, enforce the relationship at the database level:

FOREIGN KEY (order_id) REFERENCES orders(id)
This ensures that payments cannot exist without a valid order, keeping data consisten