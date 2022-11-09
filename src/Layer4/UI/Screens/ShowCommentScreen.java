package Layer4.UI.Screens;

import Layer1.Entity.Comment;
import Layer1.Entity.User;
import Layer4.Interface.csvInterface;
import Layer4.UI.Components.PlaceButton;
import Layer4.UI.Components.PlaceLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Map;

public class ShowCommentScreen extends JFrame implements ActionListener{
    public ShowCommentScreen(int userid) {
        JLabel title = new PlaceLabel().create(50,100, 200,30, "Comments Section");

        JButton addComment = new PlaceButton().create("Add Comment",null,100, 700, 150, 50);
        JButton cancel = new PlaceButton().create("Cancel",null,300, 700, 150, 50);

        addComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    AddCommentScreen addCommentScreen = new AddCommentScreen(userid);
                    addCommentScreen.setVisible(true);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });

        cancel.addActionListener(this);

        this.setLayout(null);


        this.setSize(800, 800);

        this.add(new PlaceLabel().create(400, 50, 100, 25, "UofTMeta"));
        this.add(title);
        this.add(inner());
        this.add(addComment);
        this.add(cancel);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public JScrollPane inner() {
        JPanel commentSection = new JPanel();
        commentSection.setLayout(new BoxLayout(commentSection, BoxLayout.Y_AXIS));

        csvInterface csvInteract = new csvInterface();
        try {
            Map<Integer, Comment> comments = csvInteract.commentsReader("database/comments.csv");
            Map<Integer, User> users = csvInteract.usersReader("database/user.csv");
            for (Comment c:comments.values()) {
                JLabel username = new JLabel(users.get(c.getUserId()).getUserName());
                System.out.println(username);
                JLabel comment = new JLabel(c.getContent());
                commentSection.add(username);
                commentSection.add(comment);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(commentSection);
        scrollPane.setBounds(50, 150, 700, 400);

        return scrollPane;
    }
}
