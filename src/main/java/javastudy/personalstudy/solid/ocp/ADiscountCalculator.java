
package javastudy.personalstudy.solid.ocp;

// 잘못된 예제
class ADiscountCalculator {
    public double calculateDiscount(double totalPrice, String discountType) {
        if (discountType.equals("VIP")) {
            return totalPrice * 0.8; // VIP 할인율 적용
        } else if (discountType.equals("Regular")) {
            return totalPrice * 0.9; // 정회원 할인율 적용
        }
        return totalPrice;
    }
}

// 올바른 예제
interface DiscountCalculator {
    double calculateDiscount(double totalPrice);
}

class VIPDiscountCalculator implements DiscountCalculator {
    public double calculateDiscount(double totalPrice) {
        return totalPrice * 0.8; // VIP 할인율 적용
    }
}

class RegularDiscountCalculator implements DiscountCalculator {
    public double calculateDiscount(double totalPrice) {
        return totalPrice * 0.9; // 정회원 할인율 적용
    }
}