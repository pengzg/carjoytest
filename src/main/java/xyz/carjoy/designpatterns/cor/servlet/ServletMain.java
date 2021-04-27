package main.java.xyz.carjoy.designpatterns.cor.servlet;

import sun.lwawt.macosx.CSystemTray;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.List;

public class ServletMain {

    public static void main(String args[]) {

        System.out.println("this is main");

        Msg  msg = new Msg();

        msg.setMsg("hello everyone, welcome to my web, 996 is very good, ccp is very good, :) is very good;  bikejoy.xyz is very good");

        // process msg
//        String r = msg.getMsg();
//        r = r.replaceAll("996", "955");
//        msg.setMsg(r);


//        new HtmlFilter().doFilter(msg);
//        System.out.println(msg);

//        List<Filter> filterList = new ArrayList<Filter>();
//        filterList.add(new HtmlFilter());
//        filterList.add(new SensitiveFilter());
//
//        for (Filter f :filterList) {
//            f.doFilter(msg);
//        }

//        FilterChain fc = new FilterChain();
//        fc.add(new HtmlFilter());
//        fc.add(new SensitiveFilter());
//        fc.doFilter(msg);

        FilterChain fc = new FilterChain();
        fc.add(new HtmlFilter()).add(new SensitiveFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new FaceFilter()).add(new UrlFilter());
        fc.add(fc2);
        fc.doFilter(msg);

        System.out.println(msg);

    }




}

class Msg {
    String name;
    String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}

interface  Filter{
    boolean doFilter(Msg msg);
}

class HtmlFilter implements  Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replaceAll("996", "955");
        msg.setMsg(r);
        return  true;
    }
}


class SensitiveFilter implements  Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replaceAll("ccp", "***");
        msg.setMsg(r);
//        return false;
        return true;
    }
}
class FaceFilter implements  Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replaceAll(":", "::");
        msg.setMsg(r);
        return true;
    }
}
class UrlFilter implements  Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replaceAll("bikejoy.xyz", "https://bikejoy.xyz");
        msg.setMsg(r);
        return true;
    }
}

class FilterChain implements Filter{
    List<Filter> filterList = new ArrayList<Filter>();
    public FilterChain add (Filter f) {
        filterList.add(f);
        return  this;
    }

    public boolean doFilter (Msg msg){
        for (Filter f :filterList) {
            if (!f.doFilter(msg))
                return false;
        }
        return  true;
    }

}
