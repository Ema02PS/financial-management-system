package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame implements MouseListener, MouseMotionListener {

    JPanel panel1;
    JButton BotonBuscar = new JButton();
    JButton BotonGuardar = new JButton();
    JButton BotonPrestamos = new JButton();

    ImageIcon ImagenMapa;
    JLabel Mapa = new JLabel();

    public JTextField espacioCedula = new JTextField();
    public JTextField espacioNombre = new JTextField();

    String[] listProvincias = {"San José","Alajuela","Cartago","Heredia","Guanacaste","Puntarenas","Limón"};
    public JComboBox Provincias = new JComboBox(listProvincias);

    String[] listCantones = {"San José", "Acosta", "Alajuelita", "Aserrí", "Curridabat",
            "Desamparados", "Dota", "Escazú", "Goicoechea", "León Cortés", "Montes de Oca", "Mora", "Moravia",
            "Pérez Zeledón", "Puriscal", "Santa Ana", "Tarrazú", "Tibás", "Turrubares", "Vázquez de Coronado"};
    public JComboBox Cantones = new JComboBox(listCantones);

    String[] listDistritos = {"Carmen","Catedral","Hatillo","Hospital","La Uruca","Mata Redonda","Merced",
            "Pavas","San Francisco de Dos Ríos","San Sebastián","Zapote"};
    public  JComboBox Distritos = new JComboBox(listDistritos);

    public JLabel cedula = new JLabel("Cedula");

    public Ventana() {

        setTitle("Clientes");
        this.panel1 = new JPanel();
        panel1.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        CrearContenidoVentana();
        setVisible(true);

    }

    public static void crearMostrarGUI(){
        Ventana ventana1 = new Ventana();
    }

    public void CrearContenidoVentana(){
        //JLABELS

        cedula.setBounds(20,10,50,30);
        panel1.add(cedula);

        JLabel nombre = new JLabel("Nombre");
        nombre.setBounds(20,40,50,30);
        panel1.add(nombre);

        JLabel provincia = new JLabel("Provincia");
        provincia.setBounds(20,80,80,30);
        panel1.add(provincia);

        JLabel canton = new JLabel("Canton");
        canton.setBounds(155,80,50,30);
        panel1.add(canton);

        JLabel distrito = new JLabel("Distrito");
        distrito.setBounds(315,80,50,30);
        panel1.add(distrito);

        JLabel prestamo = new JLabel("Prestamos");
        prestamo.setBounds(484,168,75,75);
        panel1.add(prestamo);

        //JComboBox lista desplegables

        Provincias.setEnabled(false);
        Provincias.setBounds(20,110,100,20);
        panel1.add(Provincias);

        Cantones.setBounds(155,110,100,20);
        panel1.add(Cantones);

        Distritos.setBounds(315,110,100,20);
        panel1.add(Distritos);

        //JTextField Caja de texto

        espacioCedula.setBounds(80,15,100,20);
        panel1.add(espacioCedula);

        espacioNombre.setBounds(80,45,100,20);
        panel1.add(espacioNombre);

        //JButton Botones

        BotonBuscar.setBounds(190,13,25,25);
        panel1.add(BotonBuscar);

        BotonGuardar.setBounds(450,105,25,25);
        panel1.add(BotonGuardar);

        BotonPrestamos.setBounds(475,220,75,75);
        panel1.add(BotonPrestamos);

        //Imágenes de Botones

        //La lupa de buscar
        ImageIcon lupabuscar = createImageIcon("/images/" + "lupa" + ".png", "lupa");
        BotonBuscar.setIcon(new ImageIcon(lupabuscar.getImage().getScaledInstance(BotonBuscar.getWidth(),BotonBuscar.getHeight(),Image.SCALE_SMOOTH)));

        //La de guardado
        ImageIcon cartuchoSave = createImageIcon("/images/" + "Cartucho" + ".png", "Cartucho");
        BotonGuardar.setIcon(new ImageIcon(cartuchoSave.getImage().getScaledInstance(BotonGuardar.getWidth(),BotonGuardar.getHeight(),Image.SCALE_SMOOTH )));

        //La del préstamo
        ImageIcon EmojiDolar = createImageIcon("/images/" + "EmojiDinero" + ".png", "EmojiDinero");
        BotonPrestamos.setIcon(new ImageIcon(EmojiDolar.getImage().getScaledInstance(BotonPrestamos.getWidth(),BotonPrestamos.getHeight(),Image.SCALE_SMOOTH )));

        //Imagen del Icono
        ImageIcon Icono = createImageIcon("/images/" + "Icono" + ".png", "Icono");
        setIconImage(Icono.getImage());

        //Imagen del mapa
        ImagenMapa = createImageIcon("/images/" + "MapaBlanco" + ".png", "MapaBlanco");
        Mapa.setBounds(10,150,430,290);
        Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
        panel1.add(Mapa);

        getContentPane().add(panel1);

    };

    public void agregarListener(ActionListener action) {
        BotonBuscar.addActionListener(action);
        BotonBuscar.setActionCommand("1");
        BotonPrestamos.addActionListener(action);
        BotonPrestamos.setActionCommand("2");
        BotonGuardar.addActionListener(action);
        BotonGuardar.setActionCommand("3");
        Provincias.addActionListener(action);
        Provincias.setActionCommand("4");
        Cantones.addActionListener(action);
        Cantones.setActionCommand("5");

    }

    //Método para encontrar la ubicación de la carpeta con las imágenes
    protected static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imageURL = Ventana.class.getResource(path);
        if (imageURL == null)
        {
            System.err.println("No se encuentra el archivo: " + path);
            return null;
        }
        else
        {
            return new ImageIcon(imageURL, description);
        }
    }

    public String[] retornaCantones(String provincia) {
        String[] stringCantones = new String[]{};
        switch (provincia) {
            case "Guanacaste":
                stringCantones = new String[]{"Liberia", "Abangares", "Bagaces", "Cañas", "Carrillo",
                        "Hojancha", "La Cruz", "Nandayure", "Nicoya", "Santa Cruz", "Tilarán"};
                break;
            case "Alajuela":
                stringCantones = new String[]{"Alajuela", "Atenas", "Grecia", "Guatuso", "Los Chiles",
                        "Naranjo", "Orotina", "Palmares", "Poás", "Río Cuarto", "San Carlos", "San Mateo", "San Ramón",
                        "Sarchí", "Upala", "Zarcero"};
                break;
            case "Heredia":
                stringCantones = new String[]{"Heredia", "Barva", "Belén", "Flores", "San Isidro",
                        "San Pablo", "San Rafael", "Santa Bárbara", "Santo Domingo", "Sarapiquí"};
                break;
            case "Limón":
                stringCantones = new String[]{"Limón", "Guácimo", "Matina", "Pococí",
                        "Siquirres", "Talamanca"};
                break;
            case "Puntarenas":
                stringCantones = new String[]{"Puntarenas", "Buenos Aires", "Corredores", "Coto Brus",
                        "Esparza", "Garabito", "Golfito", "Montes de Oro", "Osa", "Parrita", "Quepos"};
                break;
            case "Cartago":
                stringCantones = new String[]{"Cartago", "Alvarado", "El Guarco", "Jiménez", "La Unión",
                        "Oreamuno", "Paraíso", "Turrialba"};
                break;
            case "San José":
                stringCantones = new String[]{"San José", "Acosta", "Alajuelita", "Aserrí", "Curridabat",
                        "Desamparados", "Dota", "Escazú", "Goicoechea", "León Cortés", "Montes de Oca", "Mora", "Moravia",
                        "Pérez Zeledón", "Puriscal", "Santa Ana", "Tarrazú", "Tibás", "Turrubares", "Vázquez de Coronado"};
                break;

        }
        return stringCantones;
    }

    public String[] retornaDistritos(String canton) {
        String[] stringDistritos = new String[]{};
        switch (canton) {
            //Guanacaste
            case "Liberia":
                stringDistritos = new String[]{"Cañas Dulces","Curubandé","Liberia","Mayorga","Nacascolo"};
                break;
            case "Abangares":
                stringDistritos = new String[]{"Colorado","Las Juntas","San Juan","Sierra"};
                break;
            case "Bagaces":
                stringDistritos = new String[]{"Bagaces","La Fortuna","Mogote","Río Naranjo"};
                break;
            case "Cañas":
                stringDistritos = new String[]{"Bebedero","Cañas","Palmira","Porozal","San Miguel"};
                break;
            case "Carrillo":
                stringDistritos = new String[]{"Belén","Filadelfia","Palmira","Sardinal"};
                break;
            case "Hojancha":
                stringDistritos = new String[]{"Hojancha","Huacas","Monte Romo","Puerto Carrillo", "Matambú"};
                break;
            case "La Cruz":
                stringDistritos = new String[]{"La Cruz","La Garita","Santa Cecilia","Santa Elena"};
                break;
            case "Nandayure":
                stringDistritos = new String[]{"Bejuco","Carmona","Porvenir","San Pablo","Santa Rita","Zapotal"};
                break;
            case "Nicoya":
                stringDistritos = new String[]{"Belén de Nosarita","Mansión","Nicoya","Nosara","Quebrada Honda",
                        "Sámara", "San Antonio"};
                break;
            case "Santa Cruz":
                stringDistritos = new String[]{"Bolsón","Cabo Velas","Cartagena","Cuajiniquil","Diriá","Santa Cruz",
                        "Tamarindo","Tempate","Veintisiete de Abril"};
                break;
            case "Tilarán":
                stringDistritos = new String[]{"Arenal","Líbano","Quebrada Grande","Santa Rosa","Tierras Morenas",
                        "Tilarán","Tronadora"};
                break;
            //Alajuela
            case "Alajuela":
                stringDistritos = new String[]{"Alajuela","Carrizal","Desamparados","Garita","Guácima","Río Segundo",
                        "Sabanilla","San Antonio","San Isidro","San José","San Rafael","Sarapiquí","Tambor","Turrúcares"};
                break;
            case "Atenas":
                stringDistritos = new String[]{"Atenas","Concepción","Escobal","Jesús","Mercedes","San Isidro",
                        "San José","Santa Eulila"};
                break;
            case "Grecia":
                stringDistritos = new String[]{"Bolívar","Grecia","Puente de Piedra","San Isidro","San José",
                        "San Roque","Tacares"};
                break;
            case "Guatuso":
                stringDistritos = new String[]{"Buenavista","Cote","Katira","San Rafael"};
                break;
            case "Los Chiles":
                stringDistritos = new String[]{"Caño Negro","El Amparo","Los Chiles","San Jorge"};
                break;
            case "Naranjo":
                stringDistritos = new String[]{"Cirri Sur","El Rosario","Naranjo","Palmitos",
                        "San Jerónimo","San José","San Juan","San Miguel"};
                break;
            case "Orotina":
                stringDistritos = new String[]{"Coyolar","Hacienda Vieja","La Ceiba","Mastate","Orotina"};
                break;
            case "Palmares":
                stringDistritos = new String[]{"Buenos Aires","Candelaria","Esquipulas","La Granja",
                        "Palmares","Santiago","Zaragoza"};
                break;
            case "Poás":
                stringDistritos = new String[]{"Carrillos","Sabana Redonda","San Juan","San Pedro","San Rafael"};
                break;
            case "Río Cuarto":
                stringDistritos = new String[]{"Río Cuarto centro","Ángeles Norte","Bolaños","Caño Negro","Carmen",
                        "Carrizal","Colonia del Toro","Crucero","Flor","Laguna","Merced","Palmar","Palmera","Pata de Gallo",
                        "Peoresnada","Pinar","Pueblo Nuevo","San Fernando","San Gerardo","San Jorge","San Rafael","San Vicente",
                        "Santa Isabel","Santa Rita","Tabla","Bosque Alegre","El Hule","La Trinidad","Los Lagos","San José"};
                break;
            case "San Carlos":
                stringDistritos = new String[]{"Aguas Zarcas","Buena Vista","Cutris","Florencia","La Fortuna","La Palmera",
                        "La Tigra","Monterrey","Pital","Pocosol","Quesada","Venado","Venecia"};
                break;
            case "San Mateo":
                stringDistritos = new String[]{"San Mateo","Desmonte","Jesús María"};
                break;
            case "San Ramón":
                stringDistritos = new String[]{"Alfaro","Ángeles","Concepción","Peñas Blancas","Piedades Norte","Piedades Sur",
                        "San Isidro","San Juan","San Rafael","San Ramón","Santiago","Volio","Zapotal"};
                break;
            case "Sarchí":
                stringDistritos = new String[]{"Rodríguez","San Pedro","Sarchí Norte","Sarchí Sur","Toro Amarillo"};
                break;
            case "Upala":
                stringDistritos = new String[]{"Aguas Claras","Bijagua","Delicias","Dos Ríos","San José","Upala","Yolillal"};
                break;
            case "Zarcero":
                stringDistritos = new String[]{"Brisas","Guadalupe","Laguna","Palmira","Tapezco","Zapote","Zarcero"};
                break;
            //Heredia
            case "Heredia":
                stringDistritos = new String[]{"Heredia","Mercedes","San Francisco","Ulloa","Varablanca"};
                break;
            case "Barva":
                stringDistritos = new String[]{"Barva","San José de la Montaña","San Pablo","San Pedro",
                        "San Roque","Santa Lucía"};
                break;
            case "Belén":
                stringDistritos = new String[]{"La Asunción","La Rivera","San Antonio"};
                break;
            case "Flores":
                stringDistritos = new String[]{"Barrantes","Llorente","San Joaquín"};
                break;
            case "San Isidro":
                stringDistritos = new String[]{"Concepción","San Francisco","San Isidro","San José"};
                break;
            case "San Pablo":
                stringDistritos = new String[]{"Rincón de Sabanilla","San Pablo"};
                break;
            case "San Rafael":
                stringDistritos = new String[]{"Ángeles","Concepción","San Josecito","San Rafael","Santiago"};
                break;
            case "Santa Bárbara":
                stringDistritos = new String[]{"Jesús","Purabá","San Juan","San Pedro","Santa Bárbara","Santo Domingo"};
                break;
            case "Santo Domingo":
                stringDistritos = new String[]{"Pará","Paracito","San Miguel","San Vicente","Santa Rosa","Santo Domingo",
                        "Santo Tomás","Tures"};
                break;
            case "Sarapiquí":
                stringDistritos = new String[]{"Cureña","La Vírgen","Las Horquestas","Llanuras del Gaspar","Puerto Viejo"};
                break;
            //Limón
            case "Limón":
                stringDistritos = new String[]{"Limón","Matama","Río Blanco","Valle La Estrella"};
                break;
            case "Guácimo":
                stringDistritos = new String[]{"Duacarí","Guácimo","Mercedes","Pocora","Río Jiménez"};
                break;
            case "Matina":
                stringDistritos = new String[]{"Batán","Carrandi","Matina"};
                break;
            case "Pococí":
                stringDistritos = new String[]{"Cariari","Colorado","Guápiles","Jiménez","Rita","Roxana"};
                break;
            case "Siquirres":
                stringDistritos = new String[]{"Alegría","Cairo","Florida","Germania","Pacuarito","Siquirres"};
                break;
            case "Talamanca":
                stringDistritos = new String[]{"Bratsi","Cahuita","Sixaola","Telire"};
                break;
            //Puntarenas
            case "Puntarenas":
                stringDistritos = new String[]{"Acapulco","Arancibia","Barranca","Chacarita","Chira","Chomes",
                        "Cóbano","El Roble","Guacimal","Isla del Coco","Lepanto","Manzanillo","Monteverde","Paquera",
                        "Pitahaya","Puntarenas"};
                break;
            case "Buenos Aires":
                stringDistritos = new String[]{"Biolley","Boruca","Brunka","Buenos Aires","Chánguena","Colinas","Pilas",
                        "Potrero Grande","Volcán"};
                break;
            case "Corredores":
                stringDistritos = new String[]{"Corredor","La Cuesta","Laurel","Paso Canoas"};
                break;
            case "Coto Brus":
                stringDistritos = new String[]{"Aguabuena","Limoncito","Pittier","Sabalito","San Vito","Gutiérrez Brown"};
                break;
            case "Esparza":
                stringDistritos = new String[]{"Espíritu Santo","Macacona","San Jerónimo","San Juan Grande","San Rafael"};
                break;
            case "Garabito":
                stringDistritos = new String[]{"Jacó","Tárcoles"};
                break;
            case "Golfito":
                stringDistritos = new String[]{"Golfito","Guayará","Pavón","Puerto Jiménez"};
                break;
            case "Montes de Oro":
                stringDistritos = new String[]{"La Unión","Miramar","San Isidro"};
                break;
            case "Osa":
                stringDistritos = new String[]{"Bahía Ballena","Palmar","Piedras Blancas","Puerto Cortés","Sierpe"};
                break;
            case "Parrita":
                stringDistritos = new String[]{"Parrita"};
                break;
            case "Quepos":
                stringDistritos = new String[]{"Quepos","Naranjito","Savegre"};
                break;
            //Cartago
            case "Cartago":
                stringDistritos = new String[]{"Agua Caliente","Carmen","Corralillo","Dulce Nombre","Guadalupe",
                        "Llano Grande","Occidental","Oriental","Quebradilla","San Nicolás","Tierra Blanca"};
                break;
            case "Alvarado":
                stringDistritos = new String[]{"Capellades","Cervantes","Pacayas"};
                break;
            case "El Guarco":
                stringDistritos = new String[]{"Patio de Agua","San Isidro","Tejar","Tobosi"};
                break;
            case "Jiménez":
                stringDistritos = new String[]{"Juan Viñas","Pejibaye","Tucurrique"};
                break;
            case "La Unión":
                stringDistritos = new String[]{"Concepción","Dulce Nombre","Río Azul","San Diego","San Juan","San Rafael",
                        "San Ramón","Tres Ríos"};
                break;
            case "Oreamuno":
                stringDistritos = new String[]{"Cipreses","Cot","Potrero Cerrado","San Rafael","Santa Rosa"};
                break;
            case "Paraíso":
                stringDistritos = new String[]{"Cachí","Llanos de Santa Lucía","Orosí","Paraíso","Santiago de Paraíso"};
                break;
            case "Turrialba":
                stringDistritos = new String[]{"Chirripó","La Isabel","La Suiza","Pavones","Peralta","Santa Cruz","Santa Rosa",
                        "Santa Teresita","Tayutic","Tres Equis","Tuis","Turrialba"};
                break;
            //San José
            case "San José":
                stringDistritos = new String[]{"Carmen","Catedral","Hatillo","Hospital","La Uruca","Mata Redonda","Merced",
                        "Pavas","San Francisco de Dos Ríos","San Sebastián","Zapote"};
                break;
            case "Acosta":
                stringDistritos = new String[]{"Cangrejal","Guaitil","Palmichal","Sabanillas","San Ignacio"};
                break;
            case "Alajuelita":
                stringDistritos = new String[]{"Alajuelita","Concepción","San Antonio","San Felipe","San Josecito"};
                break;
            case "Aserrí":
                stringDistritos = new String[]{"Aserrí","Legua","Monterrey","Salitrillos","San Gabriel",
                        "Tarbaca","Vuelta de Jorco"};
                break;
            case "Curridabat":
                stringDistritos = new String[]{"Curridabat","Granadilla","Sánchez","Tirrases"};
                break;
            case "Desamparados":
                stringDistritos = new String[]{"Damas","Desamparados","Frailes","Gravilias","Los Guido","Patarrá",
                        "Rosario","San Antonio","San Cristóbal","San Juan de Dios","San Miguel",
                        "San Rafael Abajo","San Rafael Arriba"};
                break;
            case "Dota":
                stringDistritos = new String[]{"Copey","Jardín","Santa María"};
                break;
            case "Escazú":
                stringDistritos = new String[]{"Escazú", "San Antonio","San Rafael"};
                break;
            case "Goicoechea":
                stringDistritos = new String[]{"Calle Blancos","Guadalupe","Ipís","Mata de Plátano","Purral",
                        "Rancho Redondo","San Francisco"};
                break;
            case "León Cortés":
                stringDistritos = new String[]{"San Pablo","San Andrés","Llano Bonito","San Isidro",
                        "Santa Cruz","San Antonio"};
                break;
            case "Montes de Oca":
                stringDistritos = new String[]{"Mercedes","Sabanilla","San Pedro","San Rafael"};
                break;
            case "Mora":
                stringDistritos = new String[]{"Ciudad Colón","Guayabo","Jaris","Picagres","Piedras Negras","Tabarcia"};
                break;
            case "Moravia":
                stringDistritos = new String[]{"San Jerónimo","San Vicente","Trinidad"};
                break;
            case "Pérez Zeledón":
                stringDistritos = new String[]{"San Isidro de El General","Barú","Cajón","Daniel Flores","El General",
                        "La Amistad","Páramo","Pejibaye","Platanares","Río Nuevo","Rivas","San Pedro"};
                break;
            case "Puriscal":
                stringDistritos = new String[]{"Barbacoas","Candelarita","Chires","Desamparaditos","Grifo Alto","Mercedes Sur",
                        "San Antonio","San Rafael","Santiago"};
                break;
            case "Santa Ana":
                stringDistritos = new String[]{"Brasil","Piedades","Pozos","Salitral","Santa Ana","Uruca"};
                break;
            case "Tarrazú":
                stringDistritos = new String[]{"San Carlos","San Lorenzo","San Marcos"};
                break;
            case "Tibás":
                stringDistritos = new String[]{"Anselmo Llorente","Cinco Esquinas","Colima","León XIII","San Juan"};
                break;
            case "Turrubares":
                stringDistritos = new String[]{"Carara","San Juan de Mata","San Luis","San Pablo","San Pedro"};
                break;
            case "Vásquez de Coronado":
                stringDistritos = new String[]{"Cascajal","Dulce Nombre de Jesús","Patalillo","San Isidro","San Rafel"};
                break;
        }
        return stringDistritos;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public void setProvincias(JComboBox provincias) {
        Provincias = provincias;
    }

    public void setCantones(JComboBox cantones) {
        Cantones = cantones;
    }

    public void setDistritos(JComboBox distritos) {
        Distritos = distritos;
    }

    public JComboBox getProvincias() {
        return Provincias;
    }

    public JComboBox getCantones() {
        return Cantones;
    }

    public JComboBox getDistritos() {
        return Distritos;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void insertarComboCantones(String[] listaAuxCantones) {
        Cantones.removeAllItems();

        for (int i = 0; i < listaAuxCantones.length; i++) {
            Cantones.addItem(listaAuxCantones[i]);
        }
    }

    public void insertarComboDistritos(String[] listaAuxDistritos) {
        Distritos.removeAllItems();
        for (int i = 0; i < listaAuxDistritos.length; i++) {
            Distritos.addItem(listaAuxDistritos[i]);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //GUANACASTE
        if((e.getY()>164 && e.getY()<300 && e.getX()>14 && e.getX()<110) || (e.getY()>223 && e.getY() < 259 && e.getX()>112 && e.getX()<146))
        {
            Provincias.setSelectedItem("Guanacaste");
        }
        //ALAJUELA
        if((e.getY() > 182 && e.getY() < 222 && e.getX() > 116 && e.getX() < 166) ||
                (e.getY() > 185 && e.getY() < 250 && e.getX() > 165 && e.getX() < 190) ||
                (e.getY() > 185 && e.getY() < 285 && e.getX() > 190 && e.getX() < 240))
        {
            Provincias.setSelectedItem("Alajuela");
        }
        //HEREDIA
        if(e.getY() > 210 && e.getY() < 265 && e.getX() > 245 && e.getX() < 285)
        {
            Provincias.setSelectedItem("Heredia");
        }
        //LIMÓN
        if((e.getY()>210 && e.getY()<289 && e.getX()>290 && e.getX()<336) || (e.getY()>258 && e.getY()<342 && e.getX()>342 && e.getX()<368) || (e.getY()>287 && e.getY()<336 && e.getX()>339 && e.getX()<412) )
        {
            Provincias.setSelectedItem("Limón");
        }
        //CARTAGO
        if(e.getY()>280 && e.getY()<315 && e.getX()>271 && e.getX()<336)
        {
            Provincias.setSelectedItem("Cartago");
        }
        //SAN JOSE
        if((e.getY()>287 && e.getY()<318 && e.getX()>193 && e.getX()<255)||(e.getY()>318 && e.getY()<332 && e.getX()>258 && e.getX()<286)||(e.getY()>331 && e.getY()<354 && e.getX()>287 && e.getX()<325))
        {
            Provincias.setSelectedItem("San José");
        }
        //PUNTARENAS
        if ((e.getY() > 430 && e.getY() < 465 && e.getX() > 360 && e.getX() < 410) ||
                (e.getY() > 350 && e.getY() < 430 && e.getX() > 330 && e.getX() < 410) ||
                (e.getY() > 365 && e.getY() < 430 && e.getX() > 300 && e.getX() < 330) ||
                (e.getY() > 335 && e.getY() < 355 && e.getX() > 250 && e.getX() < 280) ||
                (e.getY() > 320 && e.getY() < 330 && e.getX() > 190 && e.getX() < 255) ||
                (e.getY() > 285 && e.getY() < 325 && e.getX() > 170 && e.getX() < 190) ||
                (e.getY() > 250 && e.getY() < 285 && e.getX() > 150 && e.getX() < 180) ||
                (e.getY() > 285 && e.getY() < 320 && e.getX() > 110 && e.getX() < 150))
        {
            Provincias.setSelectedItem("Puntarenas");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //GUANACASTE
        if((e.getY()>164 && e.getY()<300 && e.getX()>14 && e.getX()<110) || (e.getY()>223 && e.getY() < 259 && e.getX()>112 && e.getX()<146))
        {
            ImagenMapa = createImageIcon("/images/" + "MapaGuanacaste" + ".png", "MapaGuanacaste");
            Mapa.setBounds(10,150,430,290);
            Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
            panel1.add(Mapa);
            panel1.repaint();
        }

        //ALAJUELA
        if((e.getY() > 182 && e.getY() < 222 && e.getX() > 116 && e.getX() < 166) ||
                (e.getY() > 185 && e.getY() < 250 && e.getX() > 165 && e.getX() < 190) ||
                (e.getY() > 185 && e.getY() < 285 && e.getX() > 190 && e.getX() < 240))
        {
            ImagenMapa = createImageIcon("/images/" + "MapaAlajuela" + ".png", "MapaAlajuela");
            Mapa.setBounds(10,150,430,290);
            Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
            panel1.add(Mapa);
            panel1.repaint();
        }

        //HEREDIA
        if(e.getY() > 210 && e.getY() < 265 && e.getX() > 245 && e.getX() < 285)
        {
            ImagenMapa = createImageIcon("/images/" + "MapaHeredia" + ".png", "MapaHeredia");
            Mapa.setBounds(10,150,430,290);
            Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
            panel1.add(Mapa);
            panel1.repaint();
        }

        //LIMÓN
        if((e.getY()>210 && e.getY()<289 && e.getX()>290 && e.getX()<336) || (e.getY()>258 && e.getY()<342 && e.getX()>342 && e.getX()<368) || (e.getY()>287 && e.getY()<336 && e.getX()>339 && e.getX()<412) )
        {
            ImagenMapa = createImageIcon("/images/" + "MapaLimon" + ".png", "MapaLimon");
            Mapa.setBounds(10,150,430,290);
            Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
            panel1.add(Mapa);
            panel1.repaint();
        }

        //CARTAGO
        if(e.getY()>280 && e.getY()<315 && e.getX()>271 && e.getX()<336)
        {
            ImagenMapa = createImageIcon("/images/" + "MapaCartago" + ".png", "MapaCartago");
            Mapa.setBounds(10,150,430,290);
            Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
            panel1.add(Mapa);
            panel1.repaint();
        }

        //SAN JOSE
        if((e.getY()>287 && e.getY()<318 && e.getX()>193 && e.getX()<255)||(e.getY()>318 && e.getY()<332 && e.getX()>258 && e.getX()<286)||(e.getY()>331 && e.getY()<354 && e.getX()>287 && e.getX()<325))
        {
            ImagenMapa = createImageIcon("/images/" + "MapaSanJose" + ".png", "MapaSanJose");
            Mapa.setBounds(10,150,430,290);
            Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
            panel1.add(Mapa);
            panel1.repaint();
        }

        //PUNTARENAS
        if ((e.getY() > 430 && e.getY() < 465 && e.getX() > 360 && e.getX() < 410) ||
                (e.getY() > 350 && e.getY() < 430 && e.getX() > 330 && e.getX() < 410) ||
                (e.getY() > 365 && e.getY() < 430 && e.getX() > 300 && e.getX() < 330) ||
                (e.getY() > 335 && e.getY() < 355 && e.getX() > 250 && e.getX() < 280) ||
                (e.getY() > 320 && e.getY() < 330 && e.getX() > 190 && e.getX() < 255) ||
                (e.getY() > 285 && e.getY() < 325 && e.getX() > 170 && e.getX() < 190) ||
                (e.getY() > 250 && e.getY() < 285 && e.getX() > 150 && e.getX() < 180) ||
                (e.getY() > 285 && e.getY() < 320 && e.getX() > 110 && e.getX() < 150))
        {
            ImagenMapa = createImageIcon("/images/" + "MapaPuntarenas" + ".png", "MapaPuntarenas");
            Mapa.setBounds(10,150,430,290);
            Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
            panel1.add(Mapa);
            panel1.repaint();
        }

        //Que sucede si la flecha esta afuera del mapa

        if (!(e.getY() > 155 && e.getY() < 470 && e.getX() > 15 && e.getX() < 450)) {
            ImagenMapa = createImageIcon("/images/" + "MapaBlanco" + ".png", "MapaBlanco");
            Mapa.setBounds(10,150,430,290);
            Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
            panel1.add(Mapa);
            panel1.repaint();
        }

        //Cuadrados pequeños que me convierten en blanco el mapa
        if ((e.getY() > 300 && e.getY() < 465 && e.getX() > 15 && e.getX() < 100) ||
                (e.getY() > 320 && e.getY() < 465 && e.getX() > 100 && e.getX() < 195) ||
                (e.getY() > 335 && e.getY() < 465 && e.getX() > 195 && e.getX() < 235) ||
                (e.getY() > 350 && e.getY() < 465 && e.getX() > 235 && e.getX() < 270) ||
                (e.getY() > 365 && e.getY() < 430 && e.getX() > 270 && e.getX() < 300) ||
                (e.getY() > 430 && e.getY() < 465 && e.getX() > 270 && e.getX() < 370) ||
                (e.getY() > 325 && e.getY() < 465 && e.getX() > 415 && e.getX() < 450) ||
                (e.getY() > 270 && e.getY() < 310 && e.getX() > 415 && e.getX() < 450) ||
                (e.getY() > 255 && e.getY() < 270 && e.getX() > 370 && e.getX() < 450) ||
                (e.getY() > 155 && e.getY() < 255 && e.getX() > 340 && e.getX() < 450) ||
                (e.getY() > 155 && e.getY() < 200 && e.getX() > 240 && e.getX() < 340) ||
                (e.getY() > 155 && e.getY() < 180 && e.getX() > 115 && e.getX() < 240))
        {
                ImagenMapa = createImageIcon("/images/" + "MapaBlanco" + ".png", "MapaBlanco");
                Mapa.setBounds(10,150,430,290);
                Mapa.setIcon(new ImageIcon(ImagenMapa.getImage().getScaledInstance(430,290,Image.SCALE_SMOOTH)));
                panel1.add(Mapa);
                panel1.repaint();
        }
    }
}