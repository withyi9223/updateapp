package com.zy.builder;

import androidx.annotation.NonNull;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * <p>
 * Created by yi on 2019/12/19.
 */
public class Kfc {

    private int totalCount;
    private boolean addIce;
    private Hamburg hamburg;
    private Drink drink;
    private String remark;
    private boolean takeOut;

    public Kfc(Builder builder) {
        this.totalCount = builder.totalCount;
        this.addIce = builder.addIce;
        this.hamburg = builder.hambur;
        this.drink = builder.drink;
        this.remark = builder.remark;
        this.takeOut = builder.takeOut;
    }

    public static class Hamburg {

        private String hambugrName;

        public Hamburg(String hambugrName) {
            this.hambugrName = hambugrName;
        }

        public String getHambugrName() {
            return hambugrName;
        }

        public void setHambugrName(String hambugrName) {
            this.hambugrName = hambugrName;
        }
    }

    public static class Drink {

        private String drinkName;

        public Drink(String drinkName) {
            this.drinkName = drinkName;
        }

        public void setDrinkName(String drinkName) {
            this.drinkName = drinkName;
        }

        public String getDrinkName() {
            return drinkName;
        }
    }


    public static class Builder {

        private int totalCount = 1;
        private boolean addIce = false;
        private Hamburg hambur;
        private Drink drink;
        private String remark;
        private boolean takeOut;


        public Builder totalCount(int totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public Builder addIce(boolean addIce) {
            this.addIce = addIce;
            return this;
        }

        public Builder hamburg(Hamburg hamburg) {
            this.hambur = hamburg;
            return this;
        }

        public Builder drink(Drink drink) {
            this.drink = drink;
            return this;
        }

        public Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        public Builder takeOut(boolean takeOut) {
            this.takeOut = takeOut;
            return this;
        }

        public Kfc create() {
            return new Kfc(this);
        }

    }

    @NonNull
    @Override
    public String toString() {
        return hamburg.getHambugrName() + "；" + drink.getDrinkName() + ";" + "是否加冰："
                + addIce + ";是否带走：" + takeOut + ";份数：" + totalCount + "；备注：" + remark;
    }
}
