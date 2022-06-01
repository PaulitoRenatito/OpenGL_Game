# Space Macetation

O jogo Space Macetation se trata de uma aplicação em Java utilizando OpenGL onde uma nave (Jogador) que deve impedir as naves inimigas de chegarem  na parte baixa da tela, para isso o jogador deve destrui-las utilizando seu canhão laser ao mesmo tempo que evita ser atingido pelos inimigos.

Para se mover o jogador pode utilizar as teclas ASWD ou as setas ←↓→↑, além disso ele pode atirar com o SPAÇO ou com o boão direito do Mouse. Apertando P o jogo pausa/continua e apertando R o jogo reinicia. Com a tecla ESC a janela do jogo é fechada.

O jogo foi feito na IDE IntelliJ utilizando o conjunto de  biblioteca LWGL. As bibliotecas foram disponibilizadas na pasta lib do zip. Caso queira baixar as bibliotecas manualmente : https://www.lwjgl.org/customize  ----- https://github.com/LWJGL/lwjgl3-wiki/wiki/1.2.-Install .

Para a execução do jogo, na pasta out -> artifacts -> SpaceMacetation tem um arquivo .jar --> Para abrir e executar o arquivo .jar basta abrir o terminal(como administrador) e digitar o comando  –> java –jar c:\path\to\jar\file.jar  .

## Extras implementados:
1. **Fundo lindão:** Apesar de não ser animado o background do game é deveras bonito.
2. **Inimigos Diferentes:** Existe um inimigo *Boss* que aparece a cada 5 niveis, cada vez com 10 a mais de vida, além disso, um inimigo que aparece depois do primeiro *Boss*, esse inimigo (*KamiKaze*) vai em direção ao player e tenta se chocar contra o mesmo.
3. **Fases "curadas":** Durante as fases os tipos de inimigos e quantidade mudam.
4. **Fases Infinitas:** As fazes são geradas infinitamente.
5. **Vidas:** Apesar de não existir um display, o jogador possui 5 vidas, o inmigo *Boss* tem 10 a mais de vida a cada fase e o *KamiKaze* tem 2 de vida
6. **Sons:** O jogo possui audio ambiente no menu inicial, uma musica de fundo durante o jogo, sons de tiro, dano, explosão durante o jogo e som de game over.
7. **Manter razão de aspecto:** O jogo possui uma funcionalidade de manter razão aspecto quando a tela é redimensionada, pórem as imagens de background não ficam boas quando isso acontece.
8. **Telas:** Existem telas de Menu Inicial, Jogo e Game Over

### Criadores:
+ Ana Luísa Reis Ribeiro
+ Paulo Renato Souza Magalhães
+ Raul Ferreira da Cruz Neto
+ Vitor Hugo de Souza Martins
