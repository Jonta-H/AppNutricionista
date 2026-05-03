/**
 * Demonstração simples dos conceitos OO implementados no projeto.
 * Útil para mostrar no relatório que cada conceito UML funciona em código.

public class Main {

    public static void main(String[] args) {

        // ───── HERANÇA ──────────────────────────────────────────────────────
        // Nutricionista e Paciente herdam de Usuario.
        Nutricionista nutri = new Nutricionista(
                1L, "Dra. Ana Silva", "ana@nutri.com", "senha123",
                "11999990000", LocalDate.of(1985, 5, 10), "F", "CRN-3 12345");

        Paciente paciente = new Paciente(
                2L, "João Souza", "joao@email.com", "senha456",
                "11888880000", LocalDate.of(1990, 3, 15), "M",
                "Rua A, 100", "Sem alergias.");

        nutri.cadastrarPaciente(paciente);

        // ───── POLIMORFISMO atráves de classe abstrata ──────────────────────
        // Variável do tipo abstrato ProtocoloAvaliacao recebe instâncias
        // de subclasses diferentes — a chamada do mesmo método executa
        // implementações distintas conforme o objeto real.
        ProtocoloAvaliacao protocolo;
        Map<String, Double> dobras = new HashMap<>();
        dobras.put("peitoral",   12.0);
        dobras.put("abdominal",  20.0);
        dobras.put("coxa",       15.0);

        protocolo = new JacksonPollock3Dobras();
        double d3 = protocolo.calcularDensidadeCorporal(dobras, 33, "M");
        System.out.println("Densidade (3 dobras): " + d3);

        protocolo = new JacksonPollock7Dobras();
        dobras.put("axilarMedia",  10.0);
        dobras.put("triceps",       8.0);
        dobras.put("subescapular", 14.0);
        dobras.put("suprailiaca",  11.0);
        double d7 = protocolo.calcularDensidadeCorporal(dobras, 33, "M");
        System.out.println("Densidade (7 dobras): " + d7);

        // ───── COMPOSIÇÃO + AGREGAÇÃO ───────────────────────────────────────
        // Alimento existe sozinho (será AGREGADO):
        Alimento arroz  = new Alimento("Arroz cozido", 130, 28, 2.7, 0.3, 0.4, 100);
        Alimento frango = new Alimento("Frango grelhado", 165, 0, 31, 3.6, 0, 100);

        // Refeição é COMPOSTA por ItemRefeicao (criados dentro):
        Refeicao almoco = new Refeicao(10L, "Almoço", LocalTime.of(12, 30));
        almoco.adicionarItem(new ItemRefeicao(arroz,  150, "g"));   // agrega arroz
        almoco.adicionarItem(new ItemRefeicao(frango, 120, "g"));   // agrega frango

        // PlanoAlimentar é COMPOSTO por Refeicao:
        PlanoAlimentar plano = nutri.criarPlanoAlimentar(
                paciente, "Plano de Emagrecimento", "Perder 5 kg em 3 meses");
        plano.adicionarRefeicao(almoco);

        System.out.println("Calorias do plano: " + plano.calcularCalorias());

        // ───── CLASSE DE ASSOCIAÇÃO ─────────────────────────────────────────
        // Consulta liga Nutricionista e Paciente, com atributos próprios.
        Consulta consulta = new Consulta(
                100L, LocalDateTime.of(2026, 5, 15, 14, 30),
                nutri, paciente);
        paciente.adicionarConsulta(consulta);

        Anamnese anamnese = new Anamnese(LocalDate.now(),
                "Cansaço e dores de cabeça frequentes",
                "Aumentar hidratação e fracionar refeições");
        consulta.realizar(anamnese);

        // ───── INTERFACE em uso ─────────────────────────────────────────────
        // Sincronizavel, Autenticavel, GerenciadorHistorico, etc.
        boolean ok = nutri.validarAcesso("ana@nutri.com", "senha123");
        System.out.println("Login da nutri: " + ok);

        paciente.disponibilizarDadosOffline();   // método da interface Sincronizavel

        System.out.println(anamnese.correlacionarAnamneses()); // método da interface
    }
}
