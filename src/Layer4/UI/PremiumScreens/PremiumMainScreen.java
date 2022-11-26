package Layer4.UI.PremiumScreens;


import Layer4.UI.Components.PlaceLabel;
import Layer4.UI.PremiumScreens.FriendsScreens.FriendsScreen;
import Layer4.UI.PremiumScreens.PostScreens.PostScreen;
import Layer4.UI.PremiumScreens.TopicScreens.TopicScreen;
import Layer4.UI.Screens.LoginScreen;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PremiumMainScreen extends JFrame {

    public PremiumMainScreen(int userId, String username) throws IOException, FontFormatException {

        final String fontPathUI = "assets/fonts/KleeOne-SemiBold.ttf";
        InputStream is = new FileInputStream(new File(fontPathUI));
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        font = font.deriveFont(16f);

        List<String> greetingMessages = Arrays.asList("Hello, ", "Good day, ", "Hiya, ");
        List<String> chatters = Arrays.asList("I hope you are doing well.", "It is nice to see you!",
                "Let's check out some posts?");
        Random rand = new Random();
        int indexGreeting = rand.nextInt(greetingMessages.size());
        int indexChatter = rand.nextInt(chatters.size());

        BufferedImage logo = ImageIO.read(new File("assets/images/background.png"));
        ImageIcon imageIcon = new ImageIcon(logo);
        JLabel label = new JLabel(imageIcon);
        label.setSize(960, 540);
        Container container = getContentPane();

        this.setLayout(null);
        JLabel greeting = new PlaceLabel().create(625, 50, 200, 50,
                greetingMessages.get(indexGreeting) + username + "!");
        greeting.setFont(font);
        this.add(greeting);
        JLabel chatting = new PlaceLabel().create(625, 80, 200, 50, chatters.get(indexChatter));
        chatting.setFont(font);
        this.add(chatting);

        Icon friendIcon = new ImageIcon("assets/images/friends.png");

        JButton post = new JButton("Post");
        JButton friend = new JButton();
        JButton topic = new JButton("Topic");
        JButton logout = new JButton("Log Out");

        post.setBounds(625, 150, 125, 125);
        topic.setBounds(750, 150, 125, 125);

        friend.setBounds(625, 275, 125, 125);
        friend.setIcon(friendIcon);

        logout.setBounds(750, 275, 125, 125);


        post.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PostScreen postScreen = null;
                try {
                    postScreen = new PostScreen(userId, username);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                postScreen.setVisible(true);
                dispose();

            }
        });

        friend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FriendsScreen friendsScreen = null;
                try {
                    friendsScreen = new FriendsScreen(userId, username);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                friendsScreen.setVisible(true);
                dispose();

            }
        });

        topic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopicScreen topicScreen = null;
                try {
                    topicScreen = new TopicScreen(userId, username);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                topicScreen.setVisible(true);
                dispose();

            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                dispose();

            }
        });

        this.add(post);
        this.add(friend);
        this.add(topic);
        this.add(logout);
        container.add(label);
        this.setBounds(0, 0, 960, 540);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}

