package sample;

import java.io.Serializable;

public class Sword implements WeaponInterface, Serializable {
    private int Power;
    private Hero hero;

    public Sword(Hero hero) {
        Power = 1;
        this.hero =hero;
    }

    @Override
    public void upgrade() {
        Power++;
    }

    @Override
    public void degrade() {
        if(Power>1)
            Power--;
        else {
            hero.getWeapons().remove(this);

        }


    }

    @Override
    public int getPower() {
        return Power;
    }
    @Override
    public String getName() {
        return "sword";
    }
}
