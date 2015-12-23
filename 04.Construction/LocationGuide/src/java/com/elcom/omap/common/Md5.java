/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elcom.omap.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author KiemPQ-PC
 */
public class Md5 {
    public  static String getMd5Digest(String input)
      {
          try
          {
              MessageDigest md = MessageDigest.getInstance("MD5");
              byte[] messageDigest = md.digest(input.getBytes());
              BigInteger number = new BigInteger(1,messageDigest);
              return number.toString(16);
          }
          catch(NoSuchAlgorithmException e)
          {
              throw new RuntimeException(e);
          }
      }
}
