package Factory;

import Modelo.MojitoTradicional;
import Modelo.Ultravioleta;
import Modelo.Azulito;
import Modelo.Litro;
import Modelo.MojitoFresa;

public class LitrosFactory {
    public static Litro crearLitro(String tipo) {
        switch (tipo) {
            case "Mojito Tradicional":
                return new MojitoTradicional();
            case "Mojito de Fresa":
                return new MojitoFresa();
            case "Azulito":
                return new Azulito();
            case "Ultravioleta":
                return new Ultravioleta();       
            default:
            throw new IllegalArgumentException("Tipo de litro no reconocido");
        }
    }
    public static Litro crearLitroEvento(String tipo, int litros) {
        switch (tipo) {
            case "Mojito Tradicional":
                return new MojitoTradicional();
            case "Mojito de Fresa":
                return new MojitoFresa();
            case "Azulito":
                return new Azulito();
            case "Ultravioleta":
                return new Ultravioleta();
            default:
                throw new IllegalArgumentException("Tipo de litro no válido para eventos.");
        }
    }
}
