<!doctype html>
<html>
<head>
	<title>Uploader un fichier</title>
	<style>

body, legend, label, input {
    font: normal 8pt verdana, helvetica, sans-serif;
}

fieldset {
    padding: 10px;
    border: 1px #0568CD solid;
}

legend {
    font-weight: bold;
    color: #0568CD;
}

form label {
    float: left;
    width: 200px;
    margin: 3px 0px 0px 0px;
}

form input {
    margin: 3px 3px 0px 0px;
    border: 1px #999 solid;
}

}
	</style>
</head>
<body>
	<form action="" method="POST" enctype="multipart/form-data">
		<fieldset>
			<legend>Upload de fichier</legend>
			<label for="titre">Titre</label>
			<input id="titre" type="text" name="titre">
			<br>
			<label for="theme">Theme</label>
			<input id="theme" type="text" name="theme">
			<br>
			<label for="fichier">Emplacement</label>
			<input id="fichier" type="file" name="fichier" >
			<br>
			<input type="submit" value="envoyer">
		</fieldset>	
	</form>


</body>



</html>