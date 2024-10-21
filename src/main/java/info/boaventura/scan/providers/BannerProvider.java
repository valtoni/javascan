package info.boaventura.scan.providers;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BannerProvider {

  public String getBanner() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n   .-. .--.  .--.  .--.  .--. .-..-.\n");
    sb.append("   : :: .; :: .--': .--': .; :: `: :\n");
    sb.append(" _ : ::    :`. `. : :   :    :: .` :\n");
    sb.append(": :; :: :: : _`, :: :__ : :: :: :. :\n");
    sb.append("`.__.':_;:_;`.__.'`.__.':_;:_;:_;:_;\n");

    return sb.toString();
  }

  public String getVersion() {
    return "1.0.0-SNAPSHOT";
  }

  public String getWelcomeMessage() {
    return "JASCAN % Release " + getVersion() + " in " + ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME) +
        "\n\nProudly builded in 2023 by Valtoni Boaventura (valtoni@gmail.com)\n";
  }

  public String getProviderName() {
    return "Banner Jascan";
  }

}
