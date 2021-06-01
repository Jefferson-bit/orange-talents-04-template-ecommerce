package br.co.zupacademy.jefferson.mercadolivre.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import br.co.zupacademy.jefferson.mercadolivre.usuario.Usuario;
import br.co.zupacademy.jefferson.mercadolivre.usuario.UsuarioRepository;

@SuppressWarnings("deprecation")
@Component
public class JWTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Optional<Usuario> user = userRepository.findByEmail(authentication.getName());

		Map<String, Object> map = new HashMap<>();
		map.put("id", user.get().getId());
		map.put("email", user.get().getEmail());
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map);
		return accessToken;
	}
}