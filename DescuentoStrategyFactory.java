import java.util.HashMap;
import java.util.Map;

public class DescuentoStrategyFactory {
    private Map<String, DescuentoStrategy> strategies;

    public DescuentoStrategyFactory() {
        strategies = new HashMap<>();
        strategies.put("VIP", subtotal -> subtotal * 0.20);
        strategies.put("FRECUENTE", subtotal -> subtotal * 0.10);
        strategies.put("REGULAR", subtotal -> subtotal * 0.05);
        strategies.put("NUEVO", subtotal -> 0.0);
    }

    public DescuentoStrategy getStrategy(String tipoCliente) {
        return strategies.getOrDefault(tipoCliente, subtotal -> 0.0);
    }
}
