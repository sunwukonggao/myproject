/**
 * Created by gaojc on 2015/4/28.
 */
public class Test {
    public static void main(String args[]) {
       /* List<String> right = new ArrayList<String>();
        right.add("100100");
        right.add("010010");
        right.add("001001");
        int size = right.size();
        Integer temp = 000000;
        for (int i = 0; i < size; i++) {
            temp = temp | Integer.parseInt(right.get(i), 2);
        }
        System.out.println(right.get(0));
        System.out.print(Integer.toBinaryString(temp));
        */

        System.out.print("2020.00 ".trim().length());
    }

    /**
     * 用户登录
     *
     * @author hongten
     */
    public void login() {
        //用户登录的时候，去读取cookies，并且进行持久话操作，更多的登录操作这里省略啦....
        peristShoppingCartWhenUserLogin(newUser);
    }

    /**
     * 加入购物车<br>
     * ============================================<br>
     * 用户登录前：<br>
     * 用户在选择现金券的时候，点击现金券的加入购物车的时候，会把该现金券的信息(现金券的id，购买数量)<br>
     * 传递到这里，这时候，后台要做的就是从cookie中查询出是否有相同的记录，如果有相同的记录<br>
     * 则把相应的记录更新；否则，就添加新的记录<br>
     * 用户登录后：<br>
     * 用户在登录后，如果有添加购物车操作，则不用保存到cookie中，而是直接持久化购物车信息<br>
     *
     * @throws Exception
     */
    public void addToShoppingCart() throws Exception {
        if (cashTicket == null || cashTicket.getId() == null || cashTicket.getId() < 1) {
            write("nullId");
        } else if (q == null || q == "") {
            // 购买数量，默认情况下面为1
            q = String.valueOf(1);
        } else {
            // 读取所有的cookie
            Cookie cookies[] = ServletActionContext.getRequest().getCookies();
            if (cookies == null || cookies.length < 0) {
                // 没有cookie
                System.out.println("there is no any cookie ..");
            } else {
                // 判断用户是否登录
                if (getUserInSession() == null) {
                    boolean flag = true;
                    for (Cookie c : cookies) {
                        if (c.getName().equals(Conf.IDUONA_CASHTICKET_COOKIE_STARTNAME + cashTicket.getId())) {
                            // 说明已有的cookies中有相应的cookie，就进行更新操作
                            Integer oldValue = Integer.valueOf(c.getValue());
                            Integer newValue = Integer.valueOf(oldValue + Integer.valueOf(q));
                            fixCookie(c, newValue.toString().trim());
                            flag = false;
                        }
                    }
                    // 说明已有的cookies中没有相应的cookie，就进行添加操作
                    if (flag) {
                        addCookie(Conf.IDUONA_CASHTICKET_COOKIE_STARTNAME + cashTicket.getId(), q.trim());
                    }

                    // ==================================================
                    // 测试用，读取所有的cookies
                    readShoppingCartFromCookie();
                    // ==================================================

                    write("success");
                } else {
                    // 如果用户登录，说明session存在user,这时就持久化购物车信息
                    CashTicket cashTicketTemp = cashTicketService.get(cashTicket.getId());
                    if (shoppingCartService.isExistUserAndCashTicket(getUserInSession(), cashTicketTemp)) {
                        ShoppingCart oldShoppingCart = shoppingCartService.getByUserAndCashTicket(getUserInSession(), cashTicketTemp);
                        oldShoppingCart.setAmount(oldShoppingCart.getAmount() + Integer.valueOf(q));
                        if (shoppingCartService.update(oldShoppingCart)) {
                            write("success");
                        }
                    } else {
                        ShoppingCart shoppingCartTemp = new ShoppingCart();
                        shoppingCartTemp.setAmount(Integer.valueOf(q));
                        shoppingCartTemp.setUser(getUserInSession());
                        shoppingCartTemp.setCashTicket(cashTicketTemp);
                        shoppingCartTemp.setCreateTime(new Date());
                        shoppingCartTemp.setStatusType(StatusType.POSITIVE);
                        shoppingCartTemp.setUuid(UUID.randomUUID().toString());
                        if (shoppingCartService.save(shoppingCartTemp)) {
                            write("success");
                        }
                    }
                }
            }
        }
    }

    /**
     * 从cookie中读取购物车信息
     *
     * @return
     * @throws Exception
     */
    public void readShoppingCartFromCookie() throws Exception {
        System.out.println("======================================================");
        Cookie cookies[] = ServletActionContext.getRequest().getCookies();
        if (cookies == null || cookies.length < 0) {
            // System.out.println("there is no any cookie ..");
            // 没有cookie
        } else {
            for (Cookie c : cookies) {
                System.out.println("haha there are many cookies :" + c.getName() + "    " + c.getValue());
            }
        }
    }

    /**
     * 添加cookie操作
     *
     * @param name  cookie的name
     * @param value cookie的value
     */
    public void addCookie(String name, String value) {
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(2 * 60 * 60 * 1000);// 设置为2个钟
        ServletActionContext.getResponse().addCookie(cookie);
    }

    /**
     * 更新cookie操作
     *
     * @param c     要修改的cookie
     * @param value 修改的cookie的值
     */
    public void fixCookie(Cookie c, String value) {
        c.setValue(value.trim());
        c.setMaxAge(2 * 60 * 60 * 1000);// 设置为2个钟
        ServletActionContext.getResponse().addCookie(c);
    }

    /**
     * 当用户登录的时候，持久化cookie中的购物车信息，更新为本用户的购物车信息
     */
    public void peristShoppingCartWhenUserLogin(User user) {
        if (null != user) {
            Cookie cookies[] = ServletActionContext.getRequest().getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().startsWith(Conf.IDUONA_CASHTICKET_COOKIE_STARTNAME)) {
                        // 获取cookie的名称："iduona_cashTicket_45" 和 cookie的值： "21"
                        String name = c.getName();
                        Integer amount = Integer.valueOf(Integer.valueOf(c.getValue()) + Integer.valueOf(q));
                        Integer ct_id = Integer.valueOf(name.substring(name.lastIndexOf("_") + 1));
                        CashTicket temp = cashTicketService.get(ct_id);
                        ShoppingCart shoppingCartTemp = new ShoppingCart();
                        if (null != temp) {
                            if (shoppingCartService.isExistUserAndCashTicket(user, temp)) {
                                // 进行更新操作
                                ShoppingCart oldShoppingCart = shoppingCartService.getByUserAndCashTicket(user, temp);
                                oldShoppingCart.setAmount(amount);
                                shoppingCartService.update(oldShoppingCart);
                            } else {
                                // 否则进行保存记录
                                shoppingCartTemp.setAmount(amount);
                                shoppingCartTemp.setUser(user);
                                shoppingCartTemp.setCashTicket(temp);
                                shoppingCartTemp.setCreateTime(new Date());
                                shoppingCartTemp.setStatusType(StatusType.POSITIVE);
                                shoppingCartTemp.setUuid(UUID.randomUUID().toString());
                                shoppingCartService.save(shoppingCartTemp);
                            }
                        }
                    }
                }
                // 移除所有的现金券cookies
                removeAllCookies();
            }
        }
    }

    /**
     * 移除所有的现金券cookies操作
     */
    public void removeAllCookies() {
        Cookie cookies[] = ServletActionContext.getRequest().getCookies();
        if (cookies == null || cookies.length < 0) {
            // 没有cookie
            System.out.println("there is no any cookie ..");
        } else {
            System.out.println("开始删除cookies..");
            for (Cookie c : cookies) {
                if (c.getName().startsWith(Conf.IDUONA_CASHTICKET_COOKIE_STARTNAME)) {
                    c.setMaxAge(0);// 设置为0
                    ServletActionContext.getResponse().addCookie(c);
                }
            }
        }
    }
}
