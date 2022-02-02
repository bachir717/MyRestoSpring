# MyRestoSpring

# Sommaire
1. 
2. 
3. 
4. 
5. 
6. DEVOPS
    1. [Prérequis](#prérequis)
    2. [Lancer l'application avec docker & docker-compose](#docker)
    3. [Lancer l'application sur une Ec2 aws avec terraform et ansible](#terraform)
    4. [Tester notre application](#test)


## DEVOPS 

## Prérequis <a name="prérequis"></a>


### Pour lancer l'application via docker en local 

- docker
- docker-compose

### Pour lancer avec terraform et ansible sur AWS 

- Terraform
- ansible
- Un compte AWS ayant la possibilité de créer des instances ec2 AWS
- Avoir les connaissances de base sur AWS
- Avoir des connaissances en système et réseaux


## Lancer l'application avec docker & docker-compose <a name="docker"></a> gfghfgfhhfhfg

- Récupérer le repository.
- ouvrer un terminal ou une invite de commande à la racine du repository
- effectuer la commande `docker-compose up -d`
- attendez la fin de la commande

Maintenant vous pouvez accéder à l'application à l'adresse [localhost](http://localhost)

avec les identifiants : `tlacomblez` & mdp : `123456789`


## Lancer l'application sur une Ec2 aws avec terraform et ansible <a name="terraform"></a>


### La clé d'accès et la clé secret

La clé d'accès et la clé secret sont des clé fournis par AWS 

Accéder à votre dashboard AWS de votre compte. 
Vous trouverez l'access key et la secret key dans --> `Mes informations d'identification de sécurité` --> `informations d'identification AWS IAM` 
Vous devriez voir un bouton nommé `Créer une clé d'accès`, cliquez desssus pour créer une clé d'accès.
Afficher la clé secret et sauvegarder là ! Si vous la perdez vous ne pourrez pas la récupérer il vous faudra re créer une autre clé dans ce cas.

Vous avez maintenant l'access key ( la clé d'accès étant l'ID de clé d'accès dans l'interface AWS ) et la secret key.

### La clé SSH public/privé

Pour la clé ssh il faudra la généré vous même.
Pour linux, ssh-keygen est déjà installé par défaut. Il vous suffit de faire la commande : `ssh-keygen -t rsa -b 2048` et de répondre au choix qui seront affiché.

Vous pouvez laisser le chemin par défaut ou de spécifier un chemin specifique, à vous de voir.
Pour la phrase vous pouvez laisser vide et valider.

Vous avez maintenant une clé ssh public et privé ! (vous les trouverez sous linux par défaut dans `~/.ssh/id_rsa` ou dans le chemin que vous avez précisé).

### Executer Terraform fghjkl

- Récupérer le repository.
- ouvrer dans un terminal ou une invite de commande le dossier `terraform` du repository 
- exécuter la commande `terraform init`
- exécuter la commande `terraform apply`. La commande va vous demandez de fournir dans l'ordre : l'access key, la clé ssh **PUBLIQUE**, et la clé secret
- Attendez que la commande est terminé

Si tous c'est bien passé vous devriez voir à la fin le message : `vm_public_id = xx.xx.xx.xx` copier cette adresse ! (pas de panique si vous avez déjà clear votre terminal vous pouvez voir l'adresse ip v4 de votre instance sur le dashboard AWS) 

### Configurer ansible


- ouvrez le fichier `ansible/inventory.yml` dans un éditeur de texte.
- modifier la ligne `0.0.0.0` par l'adresse ip public de votre vm récupèré précédement 
- modifier la ligne `ansible_ssh_private_key_file` par le chemin de votre clé ssh privé

### Exécuter ansible

- ouvrer dans un terminal ou une invite de commande le dossier `ansible` du repository
- exécuter la commande `ansible-playbook -i inventory.yml playbook.yml`
- Attendez la fin de la commande

### Accéder aux site

Pour accéder au site et vérifier que tout à bien fonctionner accéder via l'adresse ip public de votre vm (celle utiliser dans le fichier inventory.yml)

**ATTENTION** L'https n'est pas mis en place il faudra donc y accéder via http://L'ipPublicDeVotreVM 


## Tester notre application <a name="test"></a>

Nous avons déjà déployé notre application sur AWS vous pouvez y accéder [ici](http://18.190.18.44)
Toujours avec les identifiants : `tlacomblez` & mdp : `123456789`
