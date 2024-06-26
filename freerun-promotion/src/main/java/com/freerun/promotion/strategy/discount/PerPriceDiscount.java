package com.freerun.promotion.strategy.discount;

import com.freerun.common.utils.NumberUtils;
import com.freerun.common.utils.StringUtils;
import com.freerun.promotion.domain.po.Coupon;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PerPriceDiscount implements Discount {
    /**
     * 折扣值，如果是满减则存满减金额，如果是折扣，则存折扣率，8折就是存80
     */
    private final int discountValue;

    /**
     * 使用门槛，0：表示无门槛，其他值：最低消费金额
     */
    private final int thresholdAmount;

    /**
     * 最高优惠金额，满减最大，0：表示没有限制，不为0，则表示该券有金额的限制
     */
    private final int maxDiscountAmount;

    private final static String RULE_TEMPLATE = "每满{}减{}，上限{}";

    @Override
    public boolean canUse(int totalAmount, Coupon coupon) {
        return totalAmount >= coupon.getThresholdAmount();
    }

    @Override
    public int calculateDiscount(int totalAmount, Coupon coupon) {
        int discount = 0;
        Integer thresholdAmount = coupon.getThresholdAmount();
        Integer discountValue = coupon.getDiscountValue();
        while (totalAmount >= thresholdAmount) {
            discount += discountValue;
            totalAmount -= thresholdAmount;
        }
        return Math.min(discount, coupon.getMaxDiscountAmount());
    }

    @Override
    public String getRule(Coupon coupon) {
        return StringUtils.format(
                RULE_TEMPLATE,
                NumberUtils.scaleToStr(coupon.getThresholdAmount(), 2),
                NumberUtils.scaleToStr(coupon.getDiscountValue(), 2),
                NumberUtils.scaleToStr(coupon.getMaxDiscountAmount(), 2));
    }
}
