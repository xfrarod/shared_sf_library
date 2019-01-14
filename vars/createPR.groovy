def call (user, title, tobranch, frombranch, org){
  def COMMIT_MESSAGE = sh(script:'git log -1 --pretty=%B',
      returnStdout: true).trim()
  sh "if [ ! -d ~/.config ]; then mkdir ~/.config;fi"
  sh 'echo "github.com:" >> ~/.config/hub'
  sh "echo \"- user: ${user}\" >> ~/.config/hub"
  sh "echo \"  oauth_token: ${env.TOKEN}\" >> ~/.config/hub"
  sh 'echo "  protocol: https" >> ~/.config/hub'
  try {
      sh "git checkout ${env.BRANCH_NAME}"
      sh "hub pull-request -m \"${title}\n ${COMMIT_MESSAGE} \n From Jenkins job: ${env.BUILD_URL} \" -b ${org}:${tobranch} -h ${org}:${frombranch}"
  }catch(Exception e) {
      echo "PR already created"
  }
}
