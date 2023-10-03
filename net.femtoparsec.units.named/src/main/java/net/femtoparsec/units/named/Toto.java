package net.femtoparsec.units.named;

import net.femtoparsec.units.named.measurement.*;
import net.femtoparsec.units.named.quantity.ForceQuantity;

public class Toto {

  public static void main(String[] args) {
    final Mass mass = Mass.TONNE.create(1);
    final Length length = Length.METER.create(10_000);
    final Time time = Time.parse("1 s");

    final Speed speed = length.divide(time);
    final Acceleration acceleration = speed.divide(time);
    final Force force = acceleration.multiply(mass);
    final Energy energy = length.multiply(force);


    System.out.println(force);

    System.out.println("Energy value in J: "+energy.getValueInJoule());

    final Volume volume = length.cubic();
    final Density density = mass.divide(volume);

    System.out.println("Density : "+density);

    final Temperature vapor = Temperature.FAHRENHEIT_212;
    final Temperature ice = Temperature.FAHRENHEIT_32;
    final DeltaTemperature deltaVaporIce = vapor.subtract(ice);
    final Temperature lowerThanIce = ice.subtract(DeltaTemperature.DELTA_CELSIUS.create(5));

    System.out.printf("Ice   in celsius: %s%n",ice.toCelsius().toPrettyString("%5.1f"));
    System.out.printf("Vapor in celsius: %s%n",vapor.toCelsius().toPrettyString("%5.1f"));


    System.out.printf("Delta Vapor Ice : %s%n",deltaVaporIce);
    System.out.printf("Delta Vapor Ice : %s%n",deltaVaporIce.toDeltaFahrenheit());

    System.out.printf("Ice minus 5°dC  : %s%n",lowerThanIce.toFahrenheit().toPrettyString("%5.1f"));
    System.out.printf("Ice minus 5°dC  : %s%n",lowerThanIce.toCelsius().toPrettyString("%5.1f"));
  }
}
