package info.boaventura.scan.providers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HistoryFileNameProvider {
 
  public String getHistoryFileName() {
		return "history.log";
  }

  public String getProviderName() {
    return "JASCAN file name provider";
  }
  
}