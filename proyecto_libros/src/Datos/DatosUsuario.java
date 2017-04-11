package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;

import Entidades.Usuario;

public class DatosUsuario {

    public Usuario hacerLogin(String email, String pass) {

        Usuario usu = null;

        Conexion con = new Conexion();
        String sql = "SELECT * FROM usuarios WHERE email=? and contrasena=?";

        con.crearConexion();

        PreparedStatement ps = con.preparedStatement(sql); //Creo el prepared statement
        //cuando haya ganas mover esto adentro de Conexion	
        try {
            //Seteo los valores del preapred statement
            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usu = new Usuario(rs.getString("email"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("contrasena"), rs.getInt("estados_idestados"), rs.getInt("tipousu_idtipousu"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        con.cerrarConexion();

        return usu;

    }

    public Usuario guardarUsuario(Usuario usuario) {
        int guardado = 0;
        String sql = null;
        boolean insert;
        Conexion conexion = new Conexion();
        conexion.crearConexion();
        insert = !this.existeUsuario(usuario.getEmail());
        if (insert) {
            sql = "INSERT INTO usuarios (email, nombre, apellido, telefono, direccion, contrasena, estados_idestados, tipousu_idtipousu) VALUES (?,?,?,?,?,?,?,?)";
        } else {
            sql = "UPDATE usuarios SET nombre=?, apellido=?, telefono=?, direccion=?, contrasena=?, estados_idestados=?, tipousu_idtipousu=? WHERE email=?";
        }

        PreparedStatement ps = conexion.preparedStatement(sql);

        try {
            if (insert) {
                ps.setString(1, usuario.getEmail());
                ps.setString(2, usuario.getNombre());
                ps.setString(3, usuario.getApellido());
                ps.setString(4, usuario.getTelefono());
                ps.setString(5, usuario.getDireccion());
                ps.setString(6, usuario.getContrasena());
                ps.setInt(7, usuario.getEstado());
                ps.setInt(8, usuario.getTipousu());
            } else {
                ps.setString(1, usuario.getNombre());
                ps.setString(2, usuario.getApellido());
                ps.setString(3, usuario.getTelefono());
                ps.setString(4, usuario.getDireccion());
                ps.setString(5, usuario.getContrasena());
                ps.setInt(6, usuario.getEstado());
                ps.setInt(7, usuario.getTipousu());
                ps.setString(8, usuario.getEmail());
            }

            guardado = ps.executeUpdate();

            if (guardado != 0) {
                usuario = this.getUsuario(usuario.getEmail());
            } else {
                usuario = null;
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        conexion.cerrarConexion();

        return usuario;
    }

    public boolean existeUsuario(String email) {
        boolean existe = false;

        Conexion con = new Conexion();
        String sql = "SELECT email FROM usuarios WHERE email=?";

        con.crearConexion();

        PreparedStatement ps = con.preparedStatement(sql); //Creo el prepared statement
        //cuando haya ganas mover esto adentro de Conexion	
        try {
            //Seteo los valores del preapred statement
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                existe = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        con.cerrarConexion();

        return existe;
    }

    public Usuario getUsuario(String clave) {
        Usuario usuario = null;

        Conexion con = new Conexion();
        String sql = "SELECT * FROM usuarios WHERE email=?";

        con.crearConexion();

        PreparedStatement ps = con.preparedStatement(sql); //Creo el prepared statement
        //cuando haya ganas mover esto adentro de Conexion	
        try {
            //Seteo los valores del preapred statement
            ps.setString(1, clave);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuario = new Usuario(rs.getString("email"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("contrasena"), rs.getInt("estados_idestados"), rs.getInt("tipousu_idtipousu"));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        con.cerrarConexion();

        return usuario;
    }

}
