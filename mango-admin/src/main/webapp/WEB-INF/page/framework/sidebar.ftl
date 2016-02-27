<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">

    <#--<#items as menus>-->

        <ul class="nav" id="side-menu">

        <#list sideMenus as menu>
            <li>
                <a href="${menu.hrefUrl}"><i class="fa ${menu.navIcon} nav_icon"></i>${menu.name}
                    <#if menu.childes??>
                        <span class="fa arrow">

                    </span>
                    </#if>
                </a>
                <#if menu.childes??>
                    <ul class="nav nav-second-level">

                        <#list menu.childes as subMenu>
                            <li>
                                <a target="" href="${subMenu.hrefUrl}">${subMenu.name}</a>
                            </li>

                        </#list>

                    </ul>
                </#if>

                <!-- /.nav-second-level -->
            </li>
        </#list>
        </ul>
    <#--</#items>-->

    </div>
    <!-- /.sidebar-collapse -->
</div>