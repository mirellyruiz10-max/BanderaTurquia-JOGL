package com.mycompany.banderaturquia;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BanderaTurquia extends JFrame implements GLEventListener {

    private final GLJPanel panel;

    public BanderaTurquia() {
        setTitle("Bandera de Turquía - JOGL");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        panel = new GLJPanel(capabilities);
        panel.addGLEventListener(this);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.89f, 0.04f, 0.09f, 1.0f);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glColor3f(0.89f, 0.04f, 0.09f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glVertex2f(1.5f, 0.0f);
        gl.glVertex2f(1.5f, 1.0f);
        gl.glVertex2f(0.0f, 1.0f);
        gl.glEnd();

        dibujarCirculo(gl, 0.50f, 0.50f, 0.25f, 1.0f, 1.0f, 1.0f);

        dibujarCirculo(gl, 0.5625f, 0.50f, 0.20f, 0.89f, 0.0f, 0.05f);

        dibujarEstrella(gl, 0.81f, 0.50f, 0.12f, 0.046f, 180.0);

        gl.glFlush();
    }

    private void dibujarCirculo(GL2 gl, float cx, float cy, float radio,
            float r, float g, float b) {
        gl.glColor3f(r, g, b);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(cx, cy);

        for (int i = 0; i <= 360; i++) {
            double angulo = Math.toRadians(i);
            gl.glVertex2f(
                    cx + (float) Math.cos(angulo) * radio,
                    cy + (float) Math.sin(angulo) * radio
            );
        }

        gl.glEnd();
    }

    private void dibujarEstrella(GL2 gl, float cx, float cy,
            float radioExterior, float radioInterior, double anguloInclinacion) {

        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(cx, cy);

        for (int i = 0; i <= 10; i++) {
            double angulo = Math.toRadians(anguloInclinacion + (i * 36.0));
            float radio = (i % 2 == 0) ? radioExterior : radioInterior;

            float x = cx + (float) Math.cos(angulo) * radio;
            float y = cy + (float) Math.sin(angulo) * radio;

            gl.glVertex2f(x, y);
        }

        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 1.5, 0.0, 1.0, -1.0, 1.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BanderaTurquia::new);
    }
}
