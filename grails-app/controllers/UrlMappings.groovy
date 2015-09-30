class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.${format})?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "500"(view: '/error')

        "/api/contaUsuario"(resources: "contaUsuario")
        "/api/emprestimo/emprestar"(controller: 'emprestimo', action: 'emprestar')
        "/api/emprestimo"(resources: "emprestimo")
    }
}