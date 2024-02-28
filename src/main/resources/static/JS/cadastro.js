function formatarCPF(campo) {
    campo.value = campo.value.replace(/\D/g, ''); // Remove caracteres não numéricos
    campo.value = campo.value.replace(/(\d{3})(\d)/, '$1.$2'); // Adiciona ponto após o terceiro dígito
    campo.value = campo.value.replace(/(\d{3})(\d)/, '$1.$2'); // Adiciona outro ponto após o sexto dígito
    campo.value = campo.value.replace(/(\d{3})(\d{1,2})$/, '$1-$2'); // Adiciona hífen após o nono dígito (se houver)
}
