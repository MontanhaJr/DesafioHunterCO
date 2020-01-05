# DesafioHunterCO
Desafio feito em Java para vaga de estágio HunterCO

Vou explicar aqui um pouco de como desenvolvi o desafio...

Quando vi que iria utilizar um arquivo .json, resolvi tentar fazer parecido como tinha feito em um app para Android. Apesar de não ser exatamente igual ao android, consegui encontrar um jeito de acessar os dados do json apenas acessando a URL. Essa talvez tenha sido a parte um pouco mais complicada porque não envolvia tanta lógica e sim conhecimento de Java e bibliotecas. 
A partir daí foi mais tranquilo, trabalhei com ArrayList de objetos (que já estou bastante familiarizado) para armazenar os dados que buscava do json e tratá-los.

Uma dificuldade que acabei encontrando usando o ArrayList, é que ao analizar os dados em vários "for", acabavam repetindo os mesmos nomes/valores diversas vezes dentro do ArrayList. Tentei resolver de outras formas lógicas para adicionar só 1 vez cada, porém só consegui resolver adicionando os repetidos e depois percorrendo o ArrayList e excluindo os dados repetidos.

Quanto a interface gráfica, já tinha usado na faculdade e prefiro implementá-la para deixar o programa uma coisa mais interessante de se utilizar.

É isso, espero que gostem...
