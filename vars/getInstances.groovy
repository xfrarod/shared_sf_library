def ArrayList<String> call(){
    def tkInstanceNames = []
		def lines = sh(script: 'cd cookbook/custom_nginx/; kitchen list', returnStdout: true).split('\n')
			for (int i = 1; i < lines.size(); i++) {
				tkInstanceNames << lines[i].tokenize(' ')[0]
			}
			return tkInstanceNames
}
