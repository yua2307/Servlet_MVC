<%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Product Manage</title>
     <!-- Favicon-->
        <link rel="icon" href="/resources/images/favicon.ico" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="<c:url value="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" />"rel="stylesheet" type="text/css" >
        <link href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="<c:url value="/resources/plugins/node-waves/waves.css" />" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="<c:url value="/resources/plugins/animate-css/animate.css" />"rel="stylesheet" />

        <!-- Custom Css -->
        <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

        <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
        <link href="<c:url value="/resources/css/themes/theme-green.css" />" rel="stylesheet" />

        <link href="<c:url value="/resources/css/custom.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/paymentDetail.css" />" rel="stylesheet"> 
</head>

<body class="theme-green">
    <!-- Page Loader -->
    <div class="page-loader-wrapper">
        <div class="loader">
            <div class="preloader">
                <div class="spinner-layer pl-green">
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
            </div>
            <p>Please wait...</p>
        </div>
    </div>
    <!-- #END# Page Loader -->
    <!-- Overlay For Sidebars -->
    <div class="overlay"></div>
    <!-- #END# Overlay For Sidebars -->
    <!-- Search Bar -->
    <div class="search-bar">
        <div class="search-icon">
            <i class="material-icons">search</i>
        </div>
        <input type="text" placeholder="START TYPING...">
        <div class="close-search">
            <i class="material-icons">close</i>
        </div>
    </div>
    <!-- #END# Search Bar -->
    <!-- Top Bar -->
    <nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">

            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse">
                
            </div>
        </div>
    </nav>
<!--     #Top Bar -->
    <section>
<!--         Left Sidebar -->
        <aside id="leftsidebar" class="sidebar">
<!--             Menu -->
            <div class="menu">
                <ul class="list">
                    <li class="header">MENU</li>
                    <li>
                        <a href="listServlet">
                            <span>Home</span>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="menu-toggle">
                            <span>Product Manager</span>
                        </a>
                        <ul class="ml-menu">
                            <li>
                                <a href="addServlet">Add Product</a>
                            </li>
                            <li>
                                <a href="listServlet">List Product</a>
                            </li>
                           
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="menu-toggle">
                            <span>Category Manager</span>
                        </a>
                        <ul class="ml-menu">
                            <li>
                                <a href="addCategoryServlet">Add Category</a>
                            </li>
                            <li>
                                <a href="listCategoryServlet">List Category</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
<!--             #Menu 
             Footer -->
            <div class="legal">
                <div class="copyright">
                    &copy; 2020 <a href="javascript:void(0);">Van Chinh 2020</a>.
                </div>
                <div class="version">
                    <b>Version: </b> 2.0
                </div>
            </div>
<!--             #Footer -->
        </aside>
<!--         #END# Left Sidebar -->
    </section>

    <section class="content">
        <div class="container-fluid">
            <div class="block-header align-center">
                <h2>Product Manage</h2>
            </div>
            <!-- Basic Table -->
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        
                    
                        <div class="body table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead class="btn-success">
                                    <tr>
                                        <th style="width: 2%">Id</th>
                                        <th style="width: 10%">Category Name</th>
                                       
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                     <% 
                                        ArrayList<Category> listCate = (ArrayList<Category>) request.getAttribute("listCate");
                                        for(int i = 0;i<listCate.size();i++){
                                          
                                    %>
                                         <tr>
                                             <td style="text-align: center"><%=listCate.get(i).getIdCate()%></td>
                                             <td style="text-align: center"><%=listCate.get(i).getCateName()%></td>
                                        </tr>
                                    
                                    <%
                                        }
                                    %>
                                  
             
                                </tbody>
                            </table>
                            
                        <h1 style="display:none" id="messagesError">${message}</h1>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Basic Table -->
           
            
    </section>
            <script src="<c:url value="https://code.jquery.com/jquery-3.2.1.slim.min.js" />"></script>
            <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" />"></script>
            <script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js" />"></script>
            <!-- Bootstrap Core Js -->
            <script src="<c:url value="/resources/plugins/bootstrap/js/bootstrap.js" />"></script>

            <!-- Select Plugin Js -->
            <script src="<c:url value="/resources/plugins/bootstrap-select/js/bootstrap-select.js"/>"></script>

            <!-- Slimscroll Plugin Js -->
            <script src="<c:url value="/resources/plugins/jquery-slimscroll/jquery.slimscroll.js"/>"></script>

            <!-- Waves Effect Plugin Js -->
            <script src="<c:url value="/resources/plugins/node-waves/waves.js"/>"></script>

            <!-- Custom Js -->
            <script src="<c:url value="/resources/js/admin.js"/>"></script>
            <script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"/>"></script>
            <script src="<c:url value="//code.jquery.com/jquery-1.11.1.min.js"/>"></script>
      
</body>

</html>
