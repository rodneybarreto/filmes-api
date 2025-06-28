package br.com.rodneybarreto.filmesapi.application.core.domain;

public class Filme {

    private Long id;
    private String titulo;
    private String sinopse;
    private Integer anoLancamento;

    public Filme() {}

    public Filme(Long id, String titulo, String sinopse, Integer anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.anoLancamento = anoLancamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public static FilmeBuilder builder() {
        return new FilmeBuilder();
    }

    public static class FilmeBuilder {
        private Long id;
        private String titulo;
        private String sinopse;
        private Integer anoLancamento;

        private FilmeBuilder() {
        }

        public FilmeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FilmeBuilder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public FilmeBuilder sinopse(String sinopse) {
            this.sinopse = sinopse;
            return this;
        }

        public FilmeBuilder anoLancamento(Integer anoLancamento) {
            this.anoLancamento = anoLancamento;
            return this;
        }

        public Filme build() {
            return new Filme(id, titulo, sinopse, anoLancamento);
        }
    }

}
